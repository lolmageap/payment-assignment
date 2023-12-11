package com.example.paymentassignment.controller

import com.example.paymentassignment.bankbook.BankbookService
import com.example.paymentassignment.customer.CustomerService
import com.example.paymentassignment.market.MarketService
import com.example.paymentassignment.order.OrderService
import com.example.paymentassignment.paymenthistory.PaymentHistoryService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

/**
 * 가맹점에서 결제 서버로 가맹점 key, List<상품, 상품 갯수>, 계좌 번호, 주문번호( 날짜 + uuid 12자리 ) 를 만들어서 보냅니다.
 * 결제 서버에서 결제가 진행되면 사용자에게 가맹점 명, 결제 금액, 결제 성공 여부, 생성일, 수정일을 보내줍니다.
 *
 * 동일한 결제 요청이 5초 이내로 들어오게 된다면 early return 을 발생시킵니다. ( spring data reactive redis 도입 고려 )
 * key : 주문번호 or ( 가맹점 key, 계좌번호 ), expired time : 5초
 */
class PaymentController(
    private val bankBookService: BankbookService,
    private val customerService: CustomerService,
    private val marketService: MarketService,
    private val orderService: OrderService,
    private val paymentHistoryService: PaymentHistoryService,
) {

    @PostMapping("/api/v1/market/{market-token}/order")
    suspend fun payment(
        @PathVariable("market-token") token: String,
        @RequestBody paymentRequest: PaymentRequest,
    ) {
        val bankbook = bankBookService.getBankbookByAccountNumber(paymentRequest.accountNumber)
        customerService.matching(bankbook)
        val market = marketService.getMarket(token)

        orderService.createOrder(bankbook, market, paymentRequest.price)
    }
}
