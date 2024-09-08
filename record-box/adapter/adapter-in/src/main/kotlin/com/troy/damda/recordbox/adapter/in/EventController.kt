package com.troy.damda.recordbox.adapter.`in`

import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.logger
import com.troy.damda.recordbox.application.port.`in`.EventQuery
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/damda/v1/record-box/events")
class EventController(
    private val eventQuery: EventQuery,
) {
    private val log = logger()

    @GetMapping
    fun getEvents(@UserMgmtNo userMgmtNo: Long): List<EventQuery.EventResult> {
        log.debug("userMgmtNo => {}", userMgmtNo)
        return eventQuery.getEventsFrom(userMgmtNo)
    }
}