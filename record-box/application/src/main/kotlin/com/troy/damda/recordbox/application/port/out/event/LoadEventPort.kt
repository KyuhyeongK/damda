package com.troy.damda.recordbox.application.port.out.event

import com.troy.damda.recordbox.application.domain.Event
import com.troy.damda.recordbox.application.domain.EventRelationshipType
import com.troy.damda.recordbox.application.domain.EventType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate

interface LoadEventPort {
    fun findAllByCreatedBy(
        userMgmtNo: Long,
        iqryStartDate: LocalDate?,
        iqryEndDate: LocalDate?,
        eventTypes: List<EventType>?,
        eventRelationshipTypes: List<EventRelationshipType>?,
        pageable: Pageable,
    ): Page<Event>

    fun findById(id: Long): Event?
}