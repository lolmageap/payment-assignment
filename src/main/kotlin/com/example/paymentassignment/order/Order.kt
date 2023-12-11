package com.example.paymentassignment.order

import com.example.paymentassignment.customer.Customer
import com.example.paymentassignment.market.Market
import com.example.paymentassignment.util.BaseEntity
import org.springframework.data.annotation.Id
import java.math.BigDecimal

/**
 * 주문 정보는 id, 생성 시간( createdAt ), 수정 시간( updatedAt ),
 * 가맹점 ( 1:1 ), 회원 ( 1:1 ), 상품 정보( 1:N ) 및 수량, 총 결제 금액
 * 주문이 실패하면 exception 이 발생하고 payment history 에 FAIL 이라는 상태값을 넣어줍니다.
 *
 * 상품 정보( 1:N ), 총 결제 금액 에서 상품의 정보가 추후에 변경이 되면 주문에 영향을 받게 됩니다. ( 고민 )
 * 상품 snap shot 을 만들지... 만들게 된다면 어떻게 관리를 해야할지...
 */
class Order(
    @Id
    val id: Long = 0,

    val customer: Customer,

    val market: Market,

    val price: BigDecimal,

): BaseEntity() {
}