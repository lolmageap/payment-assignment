package com.example.paymentassignment.paymenthistory

import com.example.paymentassignment.bankbook.Bankbook
import com.example.paymentassignment.market.Market
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class PaymentHistoryService(
    private val paymentHistoryRepository: PaymentHistoryRepository
) {
    suspend fun saveHistory(
        bankbook: Bankbook,
        market: Market,
        price: BigDecimal,
        isSuccess: Boolean,
    ) {
        val paymentHistory = PaymentHistory(
            customer = bankbook.customer,
            market = market,
            price = price,
            isSuccess = isSuccess,
        )
        paymentHistoryRepository.save(paymentHistory)
    }
}
