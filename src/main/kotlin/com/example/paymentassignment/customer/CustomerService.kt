package com.example.paymentassignment.customer

import com.example.paymentassignment.bankbook.Bankbook


class CustomerService(
    private val customerRepository: CustomerRepository,
) {

    suspend fun matching(bankbook: Bankbook) {
        val exist = customerRepository.existsByBankbook(bankbook)
        if (exist) throw IllegalArgumentException()
    }

}
