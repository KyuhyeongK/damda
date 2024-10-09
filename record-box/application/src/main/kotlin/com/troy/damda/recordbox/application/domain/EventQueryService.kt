package com.troy.damda.recordbox.application.domain

import com.troy.damda.recordbox.application.port.`in`.EventQuery
import com.troy.damda.recordbox.application.port.`in`.EventQuery.*
import com.troy.damda.recordbox.application.port.out.LoadEventPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class EventQueryService(
    private val loadEventPort: LoadEventPort,
) : EventQuery {

    override fun getEventsFrom(userMgmtNo: Long, pageable: Pageable): Page<EventResult> {
        return loadEventPort.findAllByCreatedBy(userMgmtNo, pageable).map { EventResult.fromEvent(it) }
    }
}