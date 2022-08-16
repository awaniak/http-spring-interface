package com.awaniak.billing

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class BillingService: CommandLineRunner {

    @Autowired
    private lateinit var customerServiceClient: CustomerServiceClient

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(BillingService::class.java, *args)
        }
    }

    override fun run(vararg args: String?) {
        customerServiceClient.addCustomer(Customer("1", "john"))
        println("Added customer")
        val customers = customerServiceClient.getCustomers()
        println("Customers: $customers")
        val customer = customerServiceClient.getCustomer("1")
        println("Customer: $customer")
    }
}