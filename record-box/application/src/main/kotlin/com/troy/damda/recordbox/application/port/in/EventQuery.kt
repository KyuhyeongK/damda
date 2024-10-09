package com.troy.damda.recordbox.application.port.`in`

import com.troy.damda.recordbox.application.domain.Event
import com.troy.damda.recordbox.application.domain.EventRelationshipType
import com.troy.damda.recordbox.application.domain.EventType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.time.LocalDateTime

interface EventQuery {

    fun getEventsFrom(userMgmtNo: Long, pageable: Pageable): Page<EventResult>

    data class EventResult(
        val eventName: String,
        val type: EventType,
        val createdBy: String,
        val owner: String,
        val relationship: EventRelationshipType,
        val eventDate: LocalDate,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime?,
        val eventId: Long,
    ) {
        companion object {
            fun fromEvent(event: Event): EventResult {
                return EventResult(
                    event.name, event.type, event.createdBy.nickname,
                    event.owner, event.relationship, event.eventDate,
                    event.createdAt, event.updatedAt, event.id!!
                )
            }
        }
    }
}