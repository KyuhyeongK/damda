package com.troy.damda.recordbox.adapter.out.persistence

import com.troy.damda.recordbox.application.domain.Event
import com.troy.damda.recordbox.application.port.out.EventRepositoryPort
import org.springframework.stereotype.Repository

@Repository
class EventRepositoryAdapter(
    private val eventRepository: EventJpaRepository,
) : EventRepositoryPort {

    override fun findAllByCreatedBy(userMgmtNo: Long): List<Event> {
        return eventRepository.findAllByCreatedById(userMgmtNo).map { it.toDomain() }
    }
}