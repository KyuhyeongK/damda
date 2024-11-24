package com.troy.damda.recordbox.application.port.`in`.pay_history

interface DeletePayHistoryUseCase {

    fun deletePayHistory(userMgmtNo: Long, eventId: Long, payHistoryId: Long)
}