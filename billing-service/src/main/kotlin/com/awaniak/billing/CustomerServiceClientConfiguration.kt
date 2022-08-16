package com.awaniak.billing

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory


@Configuration
class CustomerServiceClientConfiguration {

    @Bean
    @Primary
    fun customerServiceWebClient(): CustomerServiceClient {
        val objectMapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
        val strategies = ExchangeStrategies.builder()
            .codecs { configurer ->
                configurer.registerDefaults(false)
                configurer.customCodecs().registerWithDefaultConfig(Jackson2JsonEncoder(objectMapper, APPLICATION_JSON))
                configurer.customCodecs().registerWithDefaultConfig(Jackson2JsonDecoder(objectMapper, APPLICATION_JSON))
            }.build()
        val client: WebClient =
            WebClient.builder().exchangeStrategies(strategies).baseUrl("http://localhost:8081").build()
        val factory: HttpServiceProxyFactory = WebClientAdapter.createHttpServiceProxyFactory(client)
        factory.afterPropertiesSet()

        return factory.createClient(CustomerServiceClient::class.java)
    }


}