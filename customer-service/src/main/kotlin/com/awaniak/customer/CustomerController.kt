package com.awaniak.customer

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/customer")
class CustomerController {

    private val customers: MutableMap<String, Customer> = mutableMapOf()

    @GetMapping
    fun getCustomers(): List<Customer> {
        return customers.values.toList()
    }

    @PostMapping
    fun addCustomer(@RequestBody customer: Customer): ResponseEntity<Any> {
        customers[customer.id] = customer
        println("Customer with id ${customer.id} saved")
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: String): Customer {
        return customers[id] ?: throw CustomerNotFoundException("Customer with id $id not found")
    }
}


@ResponseStatus(value = HttpStatus.NOT_FOUND)
class CustomerNotFoundException(message: String?) : RuntimeException(message) {
}