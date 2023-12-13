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
        orderToken: String,
    ): Order {
        val order = Order(
            customer = bankbook.customer,
            market = market,
            price = price,
            orderToken = orderToken,
        )
        return orderRepository.save(order)
    }

    fun getLastOrder(orderToken: String) =
        orderRepository.findTop1ByOrderTokenOrderByCreatedAtDesc(orderToken)


}
