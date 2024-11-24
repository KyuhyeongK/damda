package com.troy.damda.recordbox.application.port.`in`.pay_history

import com.troy.damda.recordbox.application.domain.PayHistory
import com.troy.damda.recordbox.application.domain.PayType
import java.math.BigDecimal
import java.time.LocalDateTime

interface UpdatePayHistoryUseCase {

    fun updatePayHistory(userMgmtNo: Long, request: UpdatePayHistoryRequest): UpdatePayHistoryResponse

    data class UpdatePayHistoryRequest(
        val type: PayType,
        val payAmount: BigDecimal,
        val eventId: Long,
        val payHistoryId: Long,
    )

    data class UpdatePayHistoryResponse(
        val type: PayType,
        val createdBy: String,
        val payAmount: BigDecimal,
        val eventId: Long,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime?,
        val id: Long,
    ) {
        companion object {
            fun fromDomain(payHistory: PayHistory) = UpdatePayHistoryResponse(
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