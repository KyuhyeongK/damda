package com.troy.damda.recordbox.adapter.`in`.event

import com.troy.damda.DamdaResponse
import com.troy.damda.PagingResult
import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.logger
import com.troy.damda.ok
import com.troy.damda.recordbox.application.domain.EventRelationshipType
import com.troy.damda.recordbox.application.domain.EventType
import com.troy.damda.recordbox.application.port.`in`.event.EventQuery
import com.troy.damda.recordbox.application.port.`in`.event.EventQuery.EventResult
import com.troy.damda.recordbox.application.port.`in`.event.EventQuery.GetEventsRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

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
        @RequestParam("page_size", defaultValue = "20", required = false) pageSize: Int = 20,
        @RequestParam("iqry_start_date", required = false) iqryStartDate: LocalDate? = null,
        @RequestParam("iqry_end_date", required = false) iqryEndDate: LocalDate? = null,
        @RequestParam("event_type", required = false) eventTypes: List<EventType>? = null,
        @RequestParam(
            "event_relationship_type",
            required = false
        ) eventRelationshipTypes: List<EventRelationshipType>? = null,
    ): DamdaResponse<PagingResult<EventResult>> {
        log.debug("userMgmtNo => {}", userMgmtNo)
        return ok(
            eventQuery.getEventsFrom(
                userMgmtNo,
                GetEventsRequest(pageNo, pageSize, iqryStartDate, iqryEndDate, eventTypes, eventRelationshipTypes),
            )
        )
    }

}
