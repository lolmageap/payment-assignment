package com.example.paymentassignment.market

class MarketService(
    private val marketRepository: MarketRepository,
) {
    suspend fun getMarket(token: String) =
        marketRepository.findByToken(token)
            ?: throw IllegalAccessException()

}
