package com.troy.damda.recordbox.application.domain

import com.troy.damda.PagingRequest
import com.troy.damda.PagingResult
import com.troy.damda.YN
import com.troy.damda.recordbox.application.port.`in`.EventQuery
import com.troy.damda.recordbox.application.port.`in`.EventQuery.*
import com.troy.damda.recordbox.application.port.out.LoadEventPort
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class EventQueryService(
    private val loadEventPort: LoadEventPort,
) : EventQuery {

    override fun getEventsFrom(userMgmtNo: Long, pagingRequest: PagingRequest): PagingResult<EventResult> {
        val jpaPagingRequest = PageRequest.of(pagingRequest.pageNo, pagingRequest.pageSize)
        return loadEventPort.findAllByCreatedBy(userMgmtNo, jpaPagingRequest)
            .map { EventResult.fromEvent(it) }
            .let { PagingResult(it.number, it.size, it.totalElements, YN.of(it.hasNext()), it.content) }

    }
}