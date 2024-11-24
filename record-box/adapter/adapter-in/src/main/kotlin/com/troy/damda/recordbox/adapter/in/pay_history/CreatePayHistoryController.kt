package com.troy.damda.recordbox.adapter.`in`.pay_history

import com.troy.damda.DamdaResponse
import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.logger
import com.troy.damda.ok
import com.troy.damda.recordbox.application.domain.PayType
import com.troy.damda.recordbox.application.port.`in`.pay_history.CreatePayHistoryUseCase
import com.troy.damda.recordbox.application.port.`in`.pay_history.CreatePayHistoryUseCase.CreatePayHistoryRequest
import com.troy.damda.recordbox.application.port.`in`.pay_history.CreatePayHistoryUseCase.CreatePayHistoryResponse
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/damda/v1/record-box/events")
class CreatePayHistoryController(
    private val createPayHistoryUseCase: CreatePayHistoryUseCase,
) {

    private val log = logger()

    @PostMapping("/{eventId}/pay-history")
    fun createPayHistory(
        @UserMgmtNo userMgmtNo: Long,
        @PathVariable eventId: Long,
        @RequestBody request: CreatePayHistoryApiRequest
    ): DamdaResponse<CreatePayHistoryResponse> {
        log.debug("createPayHistory :: req => {}", request)
        return ok(createPayHistoryUseCase.createPayHistory(
            userMgmtNo,
            CreatePayHistoryRequest(request.type, request.payAmount, eventId))
        )
    }

    data class CreatePayHistoryApiRequest(
        val type: PayType,
        val payAmount: BigDecimal,
    )
}