package com.troy.damda.recordbox.application.port.`in`

import com.troy.damda.PagingRequest
import com.troy.damda.PagingResult
import com.troy.damda.recordbox.application.domain.Event
import com.troy.damda.recordbox.application.domain.EventRelationshipType
import com.troy.damda.recordbox.application.domain.EventType
import java.time.LocalDate
import java.time.LocalDateTime

interface EventQuery {

    fun getEventsFrom(userMgmtNo: Long, request: GetEventsRequest): PagingResult<EventResult>

    data class GetEventsRequest(
        val pageNo: Int,
        val pageSize: Int,
        val iqryStartDate: LocalDate? = null,
        val iqryEndDate: LocalDate? = null,
    )

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