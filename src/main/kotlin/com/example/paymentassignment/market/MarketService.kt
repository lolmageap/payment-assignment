package com.example.paymentassignment.market

import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class MarketService(
    private val marketRepository: MarketRepository,
) {
    suspend fun getMarket(token: String) =
        marketRepository.findByToken(token)
            ?: throw IllegalAccessException()

    suspend fun receiveMoney(market: Market, price: BigDecimal) {
        market.bankbook.balance += price
        marketRepository.save(market)
    }

}
