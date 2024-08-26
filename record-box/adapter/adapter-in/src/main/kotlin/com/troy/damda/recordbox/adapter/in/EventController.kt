package com.troy.damda.recordbox.adapter.`in`

import com.troy.damda.recordbox.application.port.`in`.EventQuery
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/events")
class EventController(
    private val eventQuery: EventQuery,
) {

    @GetMapping
    fun getEvents(): List<EventQuery.EventResult> {
        return eventQuery.getEventsFrom(-1)
    }
}