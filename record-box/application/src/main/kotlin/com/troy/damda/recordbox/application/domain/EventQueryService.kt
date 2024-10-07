package com.troy.damda.recordbox.application.domain

import com.troy.damda.recordbox.application.port.`in`.EventQuery
import com.troy.damda.recordbox.application.port.`in`.EventQuery.*
import com.troy.damda.recordbox.application.port.out.LoadEventPort
import org.springframework.stereotype.Service

@Service
class EventQueryService(
    private val loadEventPort: LoadEventPort,
) : EventQuery {

    override fun getEventsFrom(userMgmtNo: Long): List<EventResult> {
        return loadEventPort.findAllByCreatedBy(userMgmtNo).map { EventResult.fromEvent(it) }
    }
}