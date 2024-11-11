package com.troy.damda.recordbox.application.port.out

import com.troy.damda.recordbox.application.domain.PayHistory

interface UpdatePayHistoryPort {
    fun update(payHistory: PayHistory): PayHistory
}