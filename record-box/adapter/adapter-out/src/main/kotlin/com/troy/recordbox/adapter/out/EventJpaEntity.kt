package com.troy.recordbox.adapter.out

import com.troy.recordbox.application.domain.Event
import com.troy.recordbox.application.domain.EventRelationshipType
import com.troy.recordbox.application.domain.EventType
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "event_m")
class EventJpaEntity(
    @Column(name = "event_name") private val name: String,

    @Convert(converter = EventTypeConverter::class)
    @Column(name = "event_type_cd") private val type: EventType,

    @ManyToOne private val createdBy: UserJpaEntity,
    @Column(name = "event_owner_name") private val owner: String,

    @Convert(converter = EventRelationshipTypeConverter::class)
    @Column(name = "user_relationship_type_cd") private val relationship: EventRelationshipType,
    private val eventDate: LocalDate = LocalDate.now(),
    private val createdAt: LocalDateTime = LocalDateTime.now(),
    private val updatedAt: LocalDateTime?,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
) {

    fun toDomain() = Event(
        name, type, createdBy.toDomain(), owner, relationship, eventDate, createdAt, updatedAt, id
    )
}