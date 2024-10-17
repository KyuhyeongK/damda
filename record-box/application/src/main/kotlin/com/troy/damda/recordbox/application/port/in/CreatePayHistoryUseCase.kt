package com.troy.damda.recordbox.application.port.`in`

import com.troy.damda.recordbox.application.domain.PayHistory
import com.troy.damda.recordbox.application.domain.PayType
import com.troy.damda.recordbox.application.domain.User
import java.math.BigDecimal
import java.time.LocalDateTime

interface CreatePayHistoryUseCase {

    fun createPayHistory(userMgmtNo: Long, request: CreatePayHistoryRequest): CreatePayHistoryResponse

    data class CreatePayHistoryRequest(
        val type: PayType,
        val payAmount: BigDecimal,
        val eventId: Long,
    )

    data class CreatePayHistoryResponse(
        val type: PayType,
        val createdBy: String,
        val payAmount: BigDecimal,
        val eventId: Long,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime?,
        val id: Long,
    ) {
        companion object {
            fun fromDomain(payHistory: PayHistory) = CreatePayHistoryResponse(
                type = payHistory.type,
                createdBy = payHistory.createdBy.nickname,
                payAmount = payHistory.payAmount,
                eventId = payHistory.eventId,
                createdAt = payHistory.createdAt,
                updatedAt = payHistory.updatedAt,
                id = payHistory.id!!
            )
        }
    }
}