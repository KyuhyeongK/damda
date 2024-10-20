package com.troy.damda.recordbox.application.service

import com.troy.damda.PagingResult
import com.troy.damda.YN
import com.troy.damda.recordbox.application.port.`in`.PayHistoryQuery
import com.troy.damda.recordbox.application.port.`in`.PayHistoryQuery.*
import com.troy.damda.recordbox.application.port.out.LoadPayHistoryPort
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PayHistoryQueryService(
    private val loadPayHistoryPort: LoadPayHistoryPort,
) : PayHistoryQuery {

    override fun getPayHistory(
        userMgmtMo: Long, request: GetPayHistoryRequest
    ): PagingResult<GetPayHistoryResult> {
        val jpaPagingRequest = PageRequest.of(request.pageNo, request.pageSize)
        return loadPayHistoryPort.findAllByCreatedBy(userMgmtMo, request.eventId, jpaPagingRequest)
            .map { GetPayHistoryResult.fromPayHistory(it) }.let {
                PagingResult(
                    pageNo = it.number,
                    pageSize = it.size,
                    ttcn = it.totalElements,
                    nextPageExisYN = YN.of(it.hasNext()),
                    contents = it.content,
                )
            }
    }
}