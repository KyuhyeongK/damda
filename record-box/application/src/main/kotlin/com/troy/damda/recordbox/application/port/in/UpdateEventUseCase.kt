package com.troy.damda.recordbox.application.port.`in`

import com.troy.damda.recordbox.application.domain.Event
import com.troy.damda.recordbox.application.domain.EventRelationshipType
import com.troy.damda.recordbox.application.domain.EventType
import java.time.LocalDate

interface UpdateEventUseCase {

    fun updateEvent(userMgmtNo: Long, eventId: Long, request: UpdateEventRequest): UpdateEventResponse

    data class UpdateEventRequest(
        val eventName: String?,
        val type: EventType?,
        val owner: String?,
        val relationship: EventRelationshipType?,
        val eventDate: LocalDate?,
    )

    data class UpdateEventResponse(
        val eventName: String,
        val type: EventType,
        val createdBy: String,
        val owner: String,
        val relationship: EventRelationshipType,
        val eventDate: LocalDate,
    ) {
        companion object {
            fun fromDomain(event: Event): UpdateEventResponse {
                return UpdateEventResponse(
                    event.name,
                    event.type,
                    event.createdBy.nickname,
                    event.owner,
                    event.relationship,
                    event.eventDate,
                )
            }
        }
    }


}