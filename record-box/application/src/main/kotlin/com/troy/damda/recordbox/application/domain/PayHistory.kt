package com.troy.damda.recordbox.application.domain

import com.troy.damda.YN
import java.math.BigDecimal
import java.time.LocalDateTime

class PayHistory(
    var type: PayType,
    val createdBy: User,
    var payAmount: BigDecimal,
    val eventId: Long,
    val createdAt: LocalDateTime,
    var updatedAt: LocalDateTime? = null,
    var deleteYN: YN? = YN.N,
    val id: Long? = null,
) {

    fun update(
        type: PayType? = null,
        payAmount: BigDecimal? = null,
    ) {
        if (this.deleteYN == YN.Y) {
            throw RuntimeException("이미 삭제된 납부내역")
        }

        this.type = type ?: this.type
        this.payAmount = payAmount ?: this.payAmount
        updatedAt = LocalDateTime.now()
    }
}