package com.troy.damda.recordbox.adapter.`in`

import com.troy.damda.DamdaResponse
import com.troy.damda.PagingResult
import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.ok
import com.troy.damda.recordbox.application.port.`in`.PayHistoryQuery
import com.troy.damda.recordbox.application.port.`in`.PayHistoryQuery.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/damda/v1/record-box")
class PayHistoryQueryController(
    private val payHistoryQuery: PayHistoryQuery,
) {

    @GetMapping("/{eventId}/pay-history")
    fun getPayHistory(
        @UserMgmtNo userMgmtNo: Long,
        @PathVariable eventId: Long,
        @RequestParam("page_no", defaultValue = "0", required = false) pageNo: Int = 0,
        @RequestParam("page_size", defaultValue = "20", required = false) pageSize: Int = 20,
    ): DamdaResponse<PagingResult<GetPayHistoryResult>> {
        return ok(
            payHistoryQuery.getPayHistory(userMgmtNo, GetPayHistoryRequest(pageNo, pageSize, eventId))
        )
    }
}