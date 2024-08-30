package com.troy.damda.recordbox.adapter.`in`

import com.troy.damda.common.util.logger
import com.troy.damda.recordbox.application.port.`in`.EventQuery
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/events")
class EventController(
    private val eventQuery: EventQuery,
) {
    private val log = logger()

    @GetMapping("/{userId}")
    fun getEvents(@PathVariable userId: Long): List<EventQuery.EventResult> {
        log.debug("userId => {}", userId)
        return eventQuery.getEventsFrom(userId)
    }
}