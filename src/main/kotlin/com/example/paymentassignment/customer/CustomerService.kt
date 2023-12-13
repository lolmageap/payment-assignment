package com.example.paymentassignment.customer

import org.springframework.stereotype.Service


@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
) {

    suspend fun getCustomer(id: Long) =
        customerRepository.findById(id)
            ?: throw IllegalStateException()
}
