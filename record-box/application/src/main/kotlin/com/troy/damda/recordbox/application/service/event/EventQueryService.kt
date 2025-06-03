package com.troy.damda.recordbox.application.service.event

import com.troy.damda.PagingResult
import com.troy.damda.YN
import com.troy.damda.recordbox.application.port.`in`.event.EventQuery
import com.troy.damda.recordbox.application.port.`in`.event.EventQuery.EventResult
import com.troy.damda.recordbox.application.port.`in`.event.EventQuery.GetEventsRequest
import com.troy.damda.recordbox.application.port.out.event.LoadEventPort
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class EventQueryService(
    private val loadEventPort: LoadEventPort,
) : EventQuery {

    override fun getEventsFrom(userMgmtNo: Long, request: GetEventsRequest): PagingResult<EventResult> {
        val jpaPagingRequest = PageRequest.of(request.pageNo, request.pageSize)
        return loadEventPort.findAllByCreatedBy(
            userMgmtNo,
            request.iqryStartDate,
            request.iqryEndDate,
            request.eventTypes,
            jpaPagingRequest
        )
            .map { EventResult.fromEvent(it) }
            .let { PagingResult(it.number, it.size, it.totalElements, YN.of(it.hasNext()), it.content) }

    }
}