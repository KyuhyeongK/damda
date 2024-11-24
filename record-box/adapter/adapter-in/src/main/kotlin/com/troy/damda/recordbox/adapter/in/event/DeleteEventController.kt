package com.troy.damda.recordbox.adapter.`in`.event

import com.troy.damda.DamdaResponse
import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.ok
import com.troy.damda.recordbox.application.port.`in`.event.DeleteEventUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/damda/v1/record-box/events")
class DeleteEventController(
    private val deleteEventUseCase: DeleteEventUseCase,
) {
    @DeleteMapping("/{eventId}")
    fun deleteEvent(
        @UserMgmtNo userMgmtNo: Long,
        @PathVariable eventId: Long,
    ): DamdaResponse<Unit> {
        return ok(deleteEventUseCase.deleteEvent(userMgmtNo, eventId))
    }
}