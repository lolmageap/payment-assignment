package com.example.paymentassignment.market

import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface MarketRepository : CoroutineCrudRepository<Market, Long> {
    suspend fun findByToken(token: String): Market?
}
