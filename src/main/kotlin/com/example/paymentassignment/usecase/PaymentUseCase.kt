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
    suspend fun payout(
        bankbook: Bankbook,
        market: Market,
        price: BigDecimal,
        orderToken: String,
    ) {
        checkPaymentStatus(orderToken)

        val order = orderService.saveOrder(bankbook, market, price, orderToken)

        val isSuccess =
            if (bankbook.balance < price) {
                false
            } else {
                bankbookService.payMoney(bankbook, price)
                marketService.receiveMoney(market, price)
                true
            }

        paymentHistoryService.saveHistory(bankbook, market, order, price, isSuccess)
    }

    private suspend fun checkPaymentStatus(orderToken: String) {
        orderService.getLastOrder(orderToken)
            ?.let { lastOrder ->
                val isSuccess = paymentHistoryService.isSuccessToOrder(lastOrder)
                if (isSuccess) throw IllegalStateException("이미 결제가 완료된 주문입니다.")
            }
    }

}