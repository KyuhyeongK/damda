package com.troy.damda.recordbox.application.port.`in`

import com.troy.damda.recordbox.application.domain.Event
import com.troy.damda.recordbox.application.domain.EventRelationshipType
import com.troy.damda.recordbox.application.domain.EventType
import java.time.LocalDate
import java.time.LocalDateTime

interface CreateEventUseCase {

    fun createEvent(userMgmtNo: Long, request: CreateEventRequest): CreateEventResponse

    data class CreateEventRequest(
        val eventName: String,
        val type: EventType,
        val owner: String,
        val relationship: EventRelationshipType,
        val eventDate: LocalDate,
        val createdAt: LocalDateTime?,
        val updatedAt: LocalDateTime?,
    )

    data class CreateEventResponse(
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
            fun fromDomain(event: Event): CreateEventResponse {
                return CreateEventResponse(
                    event.name,
                    event.type,
                    event.createdBy.nickname,
                    event.owner,
                    event.relationship,
                    event.eventDate,
                    event.createdAt,
                    event.updatedAt,
                )
            }
        }
    }


}