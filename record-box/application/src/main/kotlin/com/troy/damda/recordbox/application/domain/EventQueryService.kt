package com.troy.damda.recordbox.application.domain

import com.troy.damda.recordbox.application.port.`in`.EventQuery
import com.troy.damda.recordbox.application.port.out.EventRepositoryPort
import org.springframework.stereotype.Service

@Service
class EventQueryService(
    private val eventRepository: EventRepositoryPort,
) : EventQuery {

    override fun getEventsFrom(userMgmtNo: Long): List<EventQuery.EventResult> {
        return eventRepository.findAllByCreatedBy(userMgmtNo).map { EventQuery.EventResult.fromEvent(it) }
    }
}