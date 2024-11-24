package com.troy.damda.recordbox.application.port.out.pay_history

import com.troy.damda.recordbox.application.domain.PayHistory

interface UpdatePayHistoryPort {
    fun update(payHistory: PayHistory): PayHistory
}