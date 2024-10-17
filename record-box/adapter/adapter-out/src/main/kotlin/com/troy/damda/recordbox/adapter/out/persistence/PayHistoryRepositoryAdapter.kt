package com.troy.damda.recordbox.adapter.out.persistence

import com.troy.damda.recordbox.application.domain.PayHistory
import com.troy.damda.recordbox.application.port.out.CreatePayHistoryPort
import org.springframework.stereotype.Repository

@Repository
class PayHistoryRepositoryAdapter(
    private val payHistoryRepository: PayHistoryRepository,
) : CreatePayHistoryPort {

    override fun create(payHistory: PayHistory): PayHistory {
        return payHistoryRepository.save(PayHistoryEntity.fromDomain(payHistory)).toDomain()
    }
}