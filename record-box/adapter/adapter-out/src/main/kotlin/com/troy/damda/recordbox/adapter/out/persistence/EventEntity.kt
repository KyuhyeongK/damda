package com.troy.damda.recordbox.adapter.out.persistence

import com.troy.damda.YN
import com.troy.damda.recordbox.adapter.out.persistence.config.DeleteYNConverter
import com.troy.damda.recordbox.adapter.out.persistence.config.EventRelationshipTypeConverter
import com.troy.damda.recordbox.adapter.out.persistence.config.EventTypeConverter
import com.troy.damda.recordbox.application.domain.Event
import com.troy.damda.recordbox.application.domain.EventRelationshipType
import com.troy.damda.recordbox.application.domain.EventType
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "event_m")
class EventEntity(
    @Column(name = "event_name") private val name: String,

    @Convert(converter = EventTypeConverter::class)
    @Column(name = "event_type_cd") private val type: EventType,

    @ManyToOne @JoinColumn(name = "createdBy")
    private val createdBy: EventUserEntity,
    @Column(name = "event_owner_name") private val owner: String,

    @Convert(converter = EventRelationshipTypeConverter::class)
    @Column(name = "user_relationship_type_cd") private val relationship: EventRelationshipType,
    private val eventDate: LocalDate = LocalDate.now(),
    private val createdAt: LocalDateTime = LocalDateTime.now(),
    private val updatedAt: LocalDateTime?,
    @Convert(converter = DeleteYNConverter::class)
    @Column(name = "delete_yn") private val deleteYn: YN?,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id") val id: Long? = null,
) {

    companion object {
        fun fromDomain(event: Event) = EventEntity(
            event.name,
            event.type,
            EventUserEntity.fromDomain(event.createdBy),
            event.owner,
            event.relationship,
            event.eventDate,
            event.createdAt,
            event.updatedAt,
            event.deleteYN,
            event.id
        )
    }

    fun toDomain() = Event(
        name, type, createdBy.toDomain(), owner, relationship, eventDate, createdAt, updatedAt, deleteYn, id!!
    )
}