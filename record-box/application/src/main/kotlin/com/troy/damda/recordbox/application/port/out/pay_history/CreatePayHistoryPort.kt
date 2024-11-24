package com.troy.damda.recordbox.application.port.out.pay_history

import com.troy.damda.recordbox.application.domain.PayHistory

interface CreatePayHistoryPort {
    fun create(payHistory: PayHistory): PayHistory
}