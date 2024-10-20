package com.troy.damda.recordbox.application.port.`in`

import com.troy.damda.PagingResult
import com.troy.damda.recordbox.application.domain.PayHistory
import com.troy.damda.recordbox.application.domain.PayType
import com.troy.damda.recordbox.application.domain.User
import java.math.BigDecimal

interface PayHistoryQuery {

    fun getPayHistory(userMgmtMo: Long, request: GetPayHistoryRequest): PagingResult<GetPayHistoryResult>

    data class GetPayHistoryRequest(
        val pageNo: Int,
        val pageSize: Int,
        val eventId: Long,
    )

    data class GetPayHistoryResult(
        val type: PayType,
        val createdBy: User,
        val payAmount: BigDecimal,
        val payHistoryId: Long? = null,
    ) {
        companion object {
            fun fromPayHistory(payHistory: PayHistory) = GetPayHistoryResult(
                type = payHistory.type,
                createdBy = payHistory.createdBy,
                payAmount = payHistory.payAmount,
                payHistoryId = payHistory.id
            )
        }
    }
}