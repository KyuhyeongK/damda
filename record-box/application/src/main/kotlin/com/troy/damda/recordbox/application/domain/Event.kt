package com.troy.damda.recordbox.application.domain

import java.time.LocalDate
import java.time.LocalDateTime

class Event(
    var name: String,
    var type: EventType,
    val createdBy: User,
    var owner: String,
    var relationship: EventRelationshipType,
    var eventDate: LocalDate = LocalDate.now(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime? = null,
    val id: Long? = null,
) {
    fun update(
        userMgmtNo: Long,
        name: String? = null,
        type: EventType? = null,
        owner: String? = null,
        relationship: EventRelationshipType? = null,
        eventDate: LocalDate? = null,
    ) {
        if (this.createdBy.id != userMgmtNo) {
            throw RuntimeException("허용되지 않은 사용자의 수정 요청")
        }

        this.name = name ?: this.name
        this.type = type ?: this.type
        this.owner = owner ?: this.owner
        this.relationship = relationship ?: this.relationship
        this.eventDate = eventDate ?: this.eventDate
        this.updatedAt = LocalDateTime.now()
    }
}