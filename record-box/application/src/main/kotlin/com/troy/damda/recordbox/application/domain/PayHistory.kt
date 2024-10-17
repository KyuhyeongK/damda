package com.troy.damda.recordbox.application.domain

import com.troy.damda.YN
import java.math.BigDecimal
import java.time.LocalDateTime

class PayHistory(
    var type: PayType,
    val createdBy: User,
    val payAmount: BigDecimal,
    val eventId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    var deleteYN: YN? = YN.N,
    val id: Long? = null,
)