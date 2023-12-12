package com.example.paymentassignment.customer

import com.example.paymentassignment.bankbook.Bankbook
import com.example.paymentassignment.util.BaseEntity
import org.springframework.data.annotation.Id

/**
 * 사용자는 id, 이름, 생성 시간( createdAt ), 수정 시간( updatedAt ), 통장( 1:N )을 가지고 있습니다.
 *
 */
class Customer(
    @Id
    val id: Long = 0,

    val name: String,

): BaseEntity()