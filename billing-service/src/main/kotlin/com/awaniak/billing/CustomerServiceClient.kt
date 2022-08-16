package com.awaniak.billing

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange(url = "/customer-service/customer", accept = ["application/json"])
interface CustomerServiceClient {

    @GetExchange
    fun getCustomers(): List<Customer>

    @PostExchange(contentType = MediaType.APPLICATION_JSON_VALUE)
    fun addCustomer(@RequestBody customer: Customer)

    @GetExchange("/{id}")
    fun getCustomer(@PathVariable id: String): Customer

}