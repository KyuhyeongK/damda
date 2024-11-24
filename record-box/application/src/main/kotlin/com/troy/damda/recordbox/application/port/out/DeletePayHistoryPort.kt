package com.troy.damda.recordbox.application.port.out

import com.troy.damda.recordbox.application.domain.PayHistory

interface DeletePayHistoryPort {
    fun delete(payHistory: PayHistory)
}