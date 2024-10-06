package com.troy.damda.recordbox.adapter.`in`

import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.logger
import com.troy.damda.recordbox.application.port.`in`.CreateEventUseCase
import com.troy.damda.recordbox.application.port.`in`.UpdateEventUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/damda/v1/record-box/events")
class EventCommandController(
    private val createEventUseCase: CreateEventUseCase,
    private val updateEventUseCase: UpdateEventUseCase,
) {

    private val log = logger()

    @PostMapping
    fun createEvent(
        @UserMgmtNo userMgmtNo: Long,
        @RequestBody request: CreateEventUseCase.CreateEventRequest
    ): CreateEventUseCase.CreateEventResponse {
        log.debug("createEvent :: req => {}", request)
        return createEventUseCase.createEvent(userMgmtNo, request)
    }

    @PutMapping("/{eventId}")
    fun updateEvent(
        @UserMgmtNo userMgmtNo: Long,
        @PathVariable eventId: Long,
        @RequestBody request: UpdateEventUseCase.UpdateEventRequest
    ): UpdateEventUseCase.UpdateEventResponse {
        return updateEventUseCase.updateEvent(userMgmtNo, eventId, request)
    }
}