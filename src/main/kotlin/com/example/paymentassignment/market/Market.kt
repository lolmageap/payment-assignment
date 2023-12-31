package com.example.paymentassignment.market

import com.example.paymentassignment.bankbook.Bankbook
import com.example.paymentassignment.util.BaseEntity
import org.springframework.data.annotation.Id
import java.time.LocalDateTime

/**
 * 가맹점은 id, 생성 시간( createdAt ), 수정 시간( updatedAt ), 가맹점 명, 통장( 1:N ), 가맹점 active ( deleted, deletedAt )
 * 노출 되어도 괜찮은 식별자( 생성 날짜 + uuid 12자리 ) 를 가지고 있습니다.
 */
class Market(
    @Id
    val id: Long = 0,

    val token: String,

    var name: String,

    val bankbook: Bankbook,

    val isActive: Boolean,

    val isDelete: Boolean,

    val deletedAt: LocalDateTime? = null,

): BaseEntity()