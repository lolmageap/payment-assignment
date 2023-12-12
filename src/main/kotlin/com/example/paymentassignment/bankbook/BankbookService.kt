package com.example.paymentassignment.bankbook

import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.math.BigDecimal

@Service
class BankbookService(
    private val bankbookRepository: BankbookRepository,
) {

    suspend fun getBankbookByAccountNumber(accountNumber: String) =
        bankbookRepository.findByAccountNumber(accountNumber)
            ?: throw IllegalArgumentException()

    suspend fun payMoney(bankbook: Bankbook, price: BigDecimal) {
        bankbook.balance -= price
        bankbookRepository.save(bankbook)
    }

}
