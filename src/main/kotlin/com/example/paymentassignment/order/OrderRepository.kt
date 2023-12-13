package com.example.paymentassignment.order

import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface OrderRepository : CoroutineCrudRepository<Order, Long> {
    fun findTop1ByOrderTokenOrderByCreatedAtDesc(orderToken: String): Order?
}
