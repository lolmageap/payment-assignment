package com.example.paymentassignment.bankbook

import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface BankbookRepository : CoroutineCrudRepository<Bankbook, Long> {
    suspend fun findByAccountNumber(accountNumber: String): Bankbook?
}
