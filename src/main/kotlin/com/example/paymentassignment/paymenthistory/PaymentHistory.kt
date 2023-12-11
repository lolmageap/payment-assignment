package com.example.paymentassignment.paymenthistory

import com.example.paymentassignment.customer.Customer
import com.example.paymentassignment.market.Market
import com.example.paymentassignment.util.BaseEntity
import org.springframework.data.annotation.Id
import java.math.BigDecimal

/**
 * 결제 내역은 id, 생성 시간( createdAt ), 수정 시간( updatedAt ), 주문 정보, 성공 / 실패 여부 를 가지고 있습니다.
 */
class PaymentHistory(

    @Id
    val id: Long = 0,

    val customer: Customer,

    val market: Market,

    val price: BigDecimal,

    val isSuccess: Boolean,

): BaseEntity()