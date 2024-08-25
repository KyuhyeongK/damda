package com.troy.recordbox.application.domain

import com.troy.recordbox.application.port.`in`.EventQuery
import com.troy.recordbox.application.port.out.EventRepositoryPort
import org.springframework.stereotype.Service

@Service
class EventQueryService(
    private val eventRepository: EventRepositoryPort,
) : EventQuery {

    override fun getEventsFrom(userId: Long): List<EventQuery.EventResult> {
        return eventRepository.findAllByCreatedBy(userId).map { EventQuery.EventResult.fromEvent(it) }
    }
}