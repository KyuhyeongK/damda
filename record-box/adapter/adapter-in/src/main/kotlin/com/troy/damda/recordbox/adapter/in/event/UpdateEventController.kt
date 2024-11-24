package com.troy.damda.recordbox.adapter.`in`.event

import com.troy.damda.DamdaResponse
import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.ok
import com.troy.damda.recordbox.application.port.`in`.event.UpdateEventUseCase
import com.troy.damda.recordbox.application.port.`in`.event.UpdateEventUseCase.UpdateEventRequest
import com.troy.damda.recordbox.application.port.`in`.event.UpdateEventUseCase.UpdateEventResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/damda/v1/record-box/events")
class UpdateEventController(
    private val updateEventUseCase: UpdateEventUseCase,
) {
    @PutMapping("/{eventId}")
    fun updateEvent(
        @UserMgmtNo userMgmtNo: Long,
        @PathVariable eventId: Long,
        @RequestBody request: UpdateEventRequest
    ): DamdaResponse<UpdateEventResponse> {
        return ok(updateEventUseCase.updateEvent(userMgmtNo, eventId, request))
    }
}