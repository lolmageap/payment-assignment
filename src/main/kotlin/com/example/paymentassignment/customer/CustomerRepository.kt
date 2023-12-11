package com.example.paymentassignment.customer

import com.example.paymentassignment.bankbook.Bankbook
import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface CustomerRepository : CoroutineCrudRepository<Customer, Long> {
    suspend fun existsByBankbook(bankbook: Bankbook): Boolean

}
