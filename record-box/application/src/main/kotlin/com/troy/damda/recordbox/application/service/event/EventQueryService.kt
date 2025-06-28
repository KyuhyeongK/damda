package com.troy.damda.recordbox.application.service.event

import com.troy.damda.PagingResult
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
        val result = loadEventPort.findAllByCreatedBy(
            userMgmtNo,
            request.iqryStartDate,
            request.iqryEndDate,
            request.eventTypes,
            request.eventRelationshipTypes,
            jpaPagingRequest
        )
        return PagingResult(
            pageNo = result.pageNo,
            pageSize = result.pageSize,
            ttcn = result.ttcn,
            nextPageExisYN = result.nextPageExisYN,
            contents = result.contents
                .map { EventResult.fromEvent(it) }
        )

    }
}