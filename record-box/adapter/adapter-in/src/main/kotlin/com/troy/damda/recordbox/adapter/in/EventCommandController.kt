package com.troy.damda.recordbox.adapter.`in`

import com.troy.damda.DamdaResponse
import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.logger
import com.troy.damda.ok
import com.troy.damda.recordbox.application.port.`in`.CreateEventUseCase
import com.troy.damda.recordbox.application.port.`in`.CreateEventUseCase.*
import com.troy.damda.recordbox.application.port.`in`.DeleteEventUseCase
import com.troy.damda.recordbox.application.port.`in`.UpdateEventUseCase
import com.troy.damda.recordbox.application.port.`in`.UpdateEventUseCase.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/damda/v1/record-box/events")
class EventCommandController(
    private val createEventUseCase: CreateEventUseCase,
    private val updateEventUseCase: UpdateEventUseCase,
    private val deleteEventUseCase: DeleteEventUseCase,
) {

    private val log = logger()

    @PostMapping
    fun createEvent(
        @UserMgmtNo userMgmtNo: Long,
        @RequestBody request: CreateEventRequest
    ): DamdaResponse<CreateEventResponse> {
        log.debug("createEvent :: req => {}", request)
        return ok(createEventUseCase.createEvent(userMgmtNo, request))
    }

    @PutMapping("/{eventId}")
    fun updateEvent(
        @UserMgmtNo userMgmtNo: Long,
        @PathVariable eventId: Long,
        @RequestBody request: UpdateEventRequest
    ): DamdaResponse<UpdateEventResponse> {
        return ok(updateEventUseCase.updateEvent(userMgmtNo, eventId, request))
    }

    @DeleteMapping("/{eventId}")
    fun deleteEvent(
        @UserMgmtNo userMgmtNo: Long,
        @PathVariable eventId: Long,
    ): DamdaResponse<Unit> {
        return ok(deleteEventUseCase.deleteEvent(userMgmtNo, eventId))
    }
}