package com.troy.damda.recordbox.application.port.out

import com.troy.damda.recordbox.application.domain.PayHistory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface LoadPayHistoryPort {
    fun findAllByCreatedBy(userMgmtNo: Long, eventId: Long, pageable: Pageable): Page<PayHistory>
}