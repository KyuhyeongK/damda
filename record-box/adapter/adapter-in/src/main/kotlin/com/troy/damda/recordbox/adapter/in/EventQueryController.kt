package com.troy.damda.recordbox.adapter.`in`

import com.troy.damda.DamdaResponse
import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.logger
import com.troy.damda.ok
import com.troy.damda.recordbox.application.port.`in`.EventQuery
import com.troy.damda.recordbox.application.port.`in`.EventQuery.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/damda/v1/record-box/events")
class EventQueryController(
    private val eventQuery: EventQuery,
) {
    private val log = logger()

    @GetMapping
    fun getEvents(@UserMgmtNo userMgmtNo: Long): DamdaResponse<List<EventResult>> {
        log.debug("userMgmtNo => {}", userMgmtNo)
        return ok(eventQuery.getEventsFrom(userMgmtNo))
    }
}