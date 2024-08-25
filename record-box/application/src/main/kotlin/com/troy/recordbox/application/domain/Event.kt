package com.troy.recordbox.application.domain

import java.time.LocalDate
import java.time.LocalDateTime

class Event(
    val name: String,
    val type: EventType,
    val createdBy: User,
    val owner: String,
    val relationship: EventRelationshipType,
    val eventDate: LocalDate = LocalDate.now(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime?,
    val id: Long?,
)