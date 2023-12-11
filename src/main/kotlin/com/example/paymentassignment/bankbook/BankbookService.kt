package com.example.paymentassignment.bankbook

import java.lang.IllegalArgumentException

class BankbookService(
    private val bankBookRepository: BankbookRepository,
) {
    suspend fun getBankbookByAccountNumber(accountNumber: String) =
        bankBookRepository.findByAccountNumber(accountNumber)
            ?: throw IllegalArgumentException()

}
