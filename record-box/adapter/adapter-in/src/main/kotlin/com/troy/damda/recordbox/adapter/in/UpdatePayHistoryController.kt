package com.troy.damda.recordbox.adapter.`in`

import com.troy.damda.DamdaResponse
import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.ok
import com.troy.damda.recordbox.application.domain.PayType
import com.troy.damda.recordbox.application.port.`in`.UpdatePayHistoryUseCase
import com.troy.damda.recordbox.application.port.`in`.UpdatePayHistoryUseCase.*
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/damda/v1/record-box/events")
class UpdatePayHistoryController(
    private val updatePayHistoryUseCase: UpdatePayHistoryUseCase,
) {

    @PutMapping("/{eventId}/pay-history/{payHistoryId}")
    fun updatePayHistory(
        @UserMgmtNo userMgmtNo: Long,
        @PathVariable eventId: Long,
        @PathVariable payHistoryId: Long,
        @RequestBody request: UpdatePayHistoryApiRequest
    ): DamdaResponse<UpdatePayHistoryResponse> {
        return ok(
            updatePayHistoryUseCase.updatePayHistory(
                userMgmtNo,
                UpdatePayHistoryRequest(request.type, request.payAmount, eventId, payHistoryId)
            )
        )
    }

    data class UpdatePayHistoryApiRequest(
        val type: PayType,
        val payAmount: BigDecimal,
    )
}