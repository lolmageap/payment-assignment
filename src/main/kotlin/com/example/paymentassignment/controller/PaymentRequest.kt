package com.example.paymentassignment.controller

import java.math.BigDecimal

data class PaymentRequest(
    val price: BigDecimal,
    val accountNumber: String,
)