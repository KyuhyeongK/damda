package com.troy.damda.recordbox.adapter.`in`

import com.fasterxml.jackson.annotation.JsonUnwrapped
import com.troy.damda.*
import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.recordbox.application.port.`in`.EventQuery
import com.troy.damda.recordbox.application.port.`in`.EventQuery.EventResult
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/damda/v1/record-box/events")
class EventQueryController(
    private val eventQuery: EventQuery,
) {
    private val log = logger()

    @GetMapping
    fun getEvents(
        @UserMgmtNo userMgmtNo: Long,
        @RequestParam("page_no", defaultValue = "0", required = false) pageNo: Int = 0,
        @RequestParam("page_size", defaultValue = "20", required = false) pageSize: Int = 20
    ): DamdaResponse<EventResultPaging> {
        log.debug("userMgmtNo => {}", userMgmtNo)
        val events = eventQuery.getEventsFrom(userMgmtNo, PageRequest.of(pageNo, pageSize))
        val pagingInfo = PagingInfo(pageNo, pageSize, events.totalElements, YN.of(events.hasNext()))
        return ok(EventResultPaging(pagingInfo, events.content))
    }

}

data class EventResultPaging(
    @JsonUnwrapped
    val pagingInfo: PagingInfo,
    val events: List<EventResult>
)