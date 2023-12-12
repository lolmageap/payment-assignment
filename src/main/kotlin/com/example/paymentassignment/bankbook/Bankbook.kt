package com.example.paymentassignment.bankbook

import com.example.paymentassignment.customer.Customer
import com.example.paymentassignment.util.BaseEntity
import org.springframework.data.annotation.Id
import java.math.BigDecimal

/**
 * 통장은 id, 생성 시간( createdAt ), 수정 시간( updatedAt ), 계좌 번호, 잔고 를 가지고 있습니다.
 */
class Bankbook(
    @Id
    val id: Long = 0,

    val customer: Customer,

    val accountNumber: String,

    var balance: BigDecimal,

): BaseEntity()
