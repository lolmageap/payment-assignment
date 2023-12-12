package com.example.paymentassignment.usecase

import com.example.paymentassignment.bankbook.Bankbook
import com.example.paymentassignment.bankbook.BankbookService
import com.example.paymentassignment.market.Market
import com.example.paymentassignment.market.MarketService
import com.example.paymentassignment.order.OrderService
import com.example.paymentassignment.paymenthistory.PaymentHistoryService
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class PaymentUseCase(
    private val bankbookService: BankbookService,
    private val marketService: MarketService,
    private val orderService: OrderService,
    private val paymentHistoryService: PaymentHistoryService,
) {
    suspend fun payout(bankbook: Bankbook, market: Market, price: BigDecimal) {
        val isSuccess =
            if (bankbook.balance < price) {
                false
            } else {
                bankbookService.payMoney(bankbook, price)
                marketService.receiveMoney(market, price)
                orderService.saveOrder(bankbook, market, price)
                true
            }
        paymentHistoryService.saveHistory(bankbook, market, price, isSuccess)
    }

}