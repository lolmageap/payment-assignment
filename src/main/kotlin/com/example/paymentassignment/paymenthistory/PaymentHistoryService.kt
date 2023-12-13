package com.example.paymentassignment.paymenthistory

import com.example.paymentassignment.bankbook.Bankbook
import com.example.paymentassignment.customer.Customer
import com.example.paymentassignment.market.Market
import com.example.paymentassignment.order.Order
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class PaymentHistoryService(
    private val paymentHistoryRepository: PaymentHistoryRepository
) {
    suspend fun saveHistory(
        bankbook: Bankbook,
        market: Market,
        order: Order,
        price: BigDecimal,
        isSuccess: Boolean,
    ) {
        val paymentHistory = PaymentHistory(
            customer = bankbook.customer,
            market = market,
            order = order,
            price = price,
            isSuccess = isSuccess,
        )
        paymentHistoryRepository.save(paymentHistory)
    }

    fun getAll(customer: Customer) = paymentHistoryRepository.findAllByCustomer(customer)

    fun isSuccessToOrder(order: Order) =
        paymentHistoryRepository.findByOrder(order)
            ?.isSuccess
            ?: throw IllegalArgumentException()

}
