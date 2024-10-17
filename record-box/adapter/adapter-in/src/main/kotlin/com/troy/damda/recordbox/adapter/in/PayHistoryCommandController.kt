package com.troy.damda.recordbox.adapter.`in`

import com.troy.damda.DamdaResponse
import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.logger
import com.troy.damda.ok
import com.troy.damda.recordbox.application.port.`in`.CreatePayHistoryUseCase
import com.troy.damda.recordbox.application.port.`in`.CreatePayHistoryUseCase.CreatePayHistoryRequest
import com.troy.damda.recordbox.application.port.`in`.CreatePayHistoryUseCase.CreatePayHistoryResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/damda/v1/record-box/pay-history")
class PayHistoryCommandController(
    private val createPayHistoryUseCase: CreatePayHistoryUseCase,
) {

    private val log = logger()

    @PostMapping
    fun createPayHistory(
        @UserMgmtNo userMgmtNo: Long,
        @RequestBody request: CreatePayHistoryRequest
    ): DamdaResponse<CreatePayHistoryResponse> {
        log.debug("createPayHistory :: req => {}", request)
        return ok(createPayHistoryUseCase.createPayHistory(userMgmtNo, request))
    }
}