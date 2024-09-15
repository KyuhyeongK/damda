package com.troy.damda.recordbox.adapter.out.persistence

import com.troy.damda.recordbox.application.domain.Event
import com.troy.damda.recordbox.application.port.out.CreateEventPort
import com.troy.damda.recordbox.application.port.out.LoadEventPort
import org.springframework.stereotype.Repository

@Repository
class EventRepositoryAdapter(
    private val eventRepository: EventRepository,
) : LoadEventPort, CreateEventPort {

    override fun findAllByCreatedBy(userMgmtNo: Long): List<Event> {
        return eventRepository.findAllByCreatedById(userMgmtNo).map { it.toDomain() }
    }

    override fun create(event: Event): Event {
        return eventRepository.save(EventEntity.fromDomain(event)).toDomain()
    }
}