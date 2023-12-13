package com.example.paymentassignment.controller

import com.example.paymentassignment.bankbook.BankbookService
import com.example.paymentassignment.customer.CustomerService
import com.example.paymentassignment.market.MarketService
import com.example.paymentassignment.paymenthistory.PaymentHistoryService
import com.example.paymentassignment.usecase.PaymentUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * 가맹점에서 결제 서버로 가맹점 key, 계좌 번호, 주문번호( 날짜 + uuid 12자리 ) 를 만들어서 보냅니다.
 * 결제 서버에서 결제가 진행되면 사용자에게 가맹점 명, 결제 금액, 결제 성공 여부, 생성일, 수정일을 보내줍니다.
 *
 * 동일한 결제 요청이 5초 이내로 들어오게 된다면 early return 을 발생시킵니다. ( spring data reactive redis 도입 고려 )
 * key : 주문번호 or ( 가맹점 key, 계좌번호 ), expired time : 5초
 */
@RestController
class PaymentController(
    private val customerService: CustomerService,
    private val bankBookService: BankbookService,
    private val marketService: MarketService,
    private val paymentHistoryService: PaymentHistoryService,
    private val paymentUseCase: PaymentUseCase,
) {

    @PostMapping("/api/v1/market/{market-token}/order/{order-id}")
    suspend fun payment(
        @PathVariable("market-token") token: String,
        @PathVariable("order-id") orderToken: String,
        @RequestBody paymentRequest: PaymentRequest,
    ) {
        val market = marketService.getMarket(token)
        val bankbook = bankBookService.getBankbookByAccountNumber(paymentRequest.accountNumber)
        paymentUseCase.payout(bankbook, market, paymentRequest.price, orderToken)
    }

    @GetMapping("/api/v1/customer/{id}/payment-history")
    suspend fun getPaymentHistory(
        @PathVariable id: Long,
    ): List<PaymentResponse> {
        val customer = customerService.getCustomer(id)
        return paymentHistoryService.getAll(customer)
            .map(PaymentResponse::of)
    }

}
