package com.example.paymentassignment.controller

import com.example.paymentassignment.paymenthistory.PaymentHistory
import java.math.BigDecimal

data class PaymentResponse(
    val marketName: String,
    val price: BigDecimal,
    val isSuccess: Boolean,
) {
    companion object {
        fun of(paymentHistory: PaymentHistory) =
            PaymentResponse(
                marketName = paymentHistory.market.name,
                price = paymentHistory.price,
                isSuccess = paymentHistory.isSuccess,
            )

    }
}