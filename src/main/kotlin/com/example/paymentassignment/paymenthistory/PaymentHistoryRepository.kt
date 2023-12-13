package com.example.paymentassignment.paymenthistory

import com.example.paymentassignment.customer.Customer
import com.example.paymentassignment.order.Order
import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface PaymentHistoryRepository : CoroutineCrudRepository<PaymentHistory, Long> {
    fun findAllByCustomer(customer: Customer): List<PaymentHistory>
    fun findByOrder(order: Order): PaymentHistory?
}
