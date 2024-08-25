package com.troy.recordbox.adapter.out

import com.troy.recordbox.application.domain.Event
import com.troy.recordbox.application.port.out.EventRepositoryPort
import org.springframework.stereotype.Repository

@Repository
class EventRepositoryAdapter(
    private val eventRepository: EventJpaRepository,
) : EventRepositoryPort {

    override fun findAllByCreatedBy(userId: Long): List<Event> {
        return eventRepository.findAllByCreatedBy(userId).map { it.toDomain() }
    }
}