package com.example.paymentassignment.order

import com.example.paymentassignment.bankbook.Bankbook
import com.example.paymentassignment.market.Market
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {

    suspend fun saveOrder(
        bankbook: Bankbook,
        market: Market,
        price: BigDecimal,
    ) {
        val order = Order(
            customer = bankbook.customer,
            market = market,
            price = price,
        )
        orderRepository.save(order)
    }

}
