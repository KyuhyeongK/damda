package com.troy.damda.recordbox.application.port.out

import com.troy.damda.recordbox.application.domain.PayHistory

interface CreatePayHistoryPort {
    fun create(payHistory: PayHistory): PayHistory
}