package com.example.paymentassignment.order

import com.example.paymentassignment.bankbook.Bankbook
import com.example.paymentassignment.market.Market
import java.math.BigDecimal

class OrderService(
    private val orderRepository: OrderRepository,
) {
    suspend fun createOrder(bankbook: Bankbook, market: Market, price: BigDecimal) {

    }

}
