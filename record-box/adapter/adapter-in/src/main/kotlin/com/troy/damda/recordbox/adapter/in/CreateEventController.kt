package com.troy.damda.recordbox.adapter.`in`

import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.logger
import com.troy.damda.recordbox.application.port.`in`.CreateEventUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/damda/v1/record-box/events")
class CreateEventController(
    private val createEventUseCase: CreateEventUseCase,
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
}