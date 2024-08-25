package com.troy.recordbox.application.port.`in`

import com.troy.recordbox.application.domain.Event
import com.troy.recordbox.application.domain.EventRelationshipType
import com.troy.recordbox.application.domain.EventType
import java.time.LocalDate
import java.time.LocalDateTime

interface EventQuery {

    fun getEventsFrom(userId: Long): List<EventResult>

    data class EventResult(
        val eventName: String,
        val type: EventType,
        val createdBy: String,
        val owner: String,
        val relationship: EventRelationshipType,
        val eventDate: LocalDate,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime?,
    ) {
        companion object {
            fun fromEvent(event: Event): EventResult {
                return EventResult(
                    event.name, event.type, event.createdBy.nickname,
                    event.owner, event.relationship, event.eventDate,
                    event.createdAt, event.updatedAt
                )
            }
        }
    }
}