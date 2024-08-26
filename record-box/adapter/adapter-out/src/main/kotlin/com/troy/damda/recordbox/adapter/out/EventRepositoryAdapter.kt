package com.troy.damda.recordbox.adapter.out

import com.troy.damda.recordbox.application.domain.Event
import com.troy.damda.recordbox.application.port.out.EventRepositoryPort
import org.springframework.stereotype.Repository

@Repository
class EventRepositoryAdapter(
    private val eventRepository: EventJpaRepository,
) : EventRepositoryPort {

    override fun findAllByCreatedBy(userId: Long): List<Event> {
        return eventRepository.findAllByCreatedBy(userId).map { it.toDomain() }
    }
}