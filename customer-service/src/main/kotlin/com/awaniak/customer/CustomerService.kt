package com.awaniak.customer

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class CustomerService {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(CustomerService::class.java, *args)
        }
    }

}