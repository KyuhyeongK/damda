package com.troy.damda.recordbox.adapter.out.persistence

import com.troy.damda.recordbox.application.domain.Event
import com.troy.damda.recordbox.application.port.out.CreateEventPort
import com.troy.damda.recordbox.application.port.out.LoadEventPort
import com.troy.damda.recordbox.application.port.out.UpdateEventPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class EventRepositoryAdapter(
    private val eventRepository: EventRepository,
) : LoadEventPort, CreateEventPort, UpdateEventPort {

    override fun findAllByCreatedBy(userMgmtNo: Long): List<Event> {
        return eventRepository.findAllByCreatedById(userMgmtNo).map { it.toDomain() }
    }

    override fun findById(id: Long): Event? {
        return eventRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun create(event: Event): Event {
        return eventRepository.save(EventEntity.fromDomain(event)).toDomain()
    }

    override fun update(event: Event): Event {
        return create(event)
    }
}