package com.troy.damda.recordbox.adapter.`in`

import com.troy.damda.DamdaResponse
import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.ok
import com.troy.damda.recordbox.application.port.`in`.DeletePayHistoryUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/damda/v1/record-box/events")
class DeletePayHistoryController(
    private val deletePayHistoryUseCase: DeletePayHistoryUseCase,
) {

    @DeleteMapping("/{eventId}/pay-history/{payHistoryId}")
    fun deletePayHistory(
        @UserMgmtNo userMgmtNo: Long,
        @PathVariable eventId: Long,
        @PathVariable payHistoryId: Long,
    ): DamdaResponse<Unit> {
        return ok(deletePayHistoryUseCase.deletePayHistory(userMgmtNo, eventId, payHistoryId))
    }
}