package com.troy.damda.recordbox.application.port.`in`

interface DeletePayHistoryUseCase {

    fun deletePayHistory(userMgmtNo: Long, eventId: Long, payHistoryId: Long)
}