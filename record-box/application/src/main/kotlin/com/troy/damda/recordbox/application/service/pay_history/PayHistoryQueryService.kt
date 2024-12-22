package com.troy.damda.recordbox.application.service.pay_history

import com.troy.damda.DamdaException
import com.troy.damda.DamdaException.ErrorCode
import com.troy.damda.PagingResult
import com.troy.damda.YN
import com.troy.damda.recordbox.application.port.`in`.pay_history.PayHistoryQuery
import com.troy.damda.recordbox.application.port.`in`.pay_history.PayHistoryQuery.*
import com.troy.damda.recordbox.application.port.out.event.LoadEventPort
import com.troy.damda.recordbox.application.port.out.pay_history.LoadPayHistoryPort
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PayHistoryQueryService(
    private val loadEventPort: LoadEventPort,
    private val loadPayHistoryPort: LoadPayHistoryPort,
) : PayHistoryQuery {

    override fun getPayHistory(
        userMgmtMo: Long, request: GetPayHistoryRequest
    ): PagingResult<GetPayHistoryResult> {
        return loadEventPort.findById(request.eventId)?.also {
            if (it.createdBy.id != userMgmtMo) {
                throw DamdaException(ErrorCode.USER_MGMT_NO_MISMATCH, "$userMgmtMo 사용자가 작성하지 않은 이벤트")
            }
        }?.let {
            val jpaPagingRequest = PageRequest.of(request.pageNo, request.pageSize)
            loadPayHistoryPort.findAllByCreatedBy(userMgmtMo, request.eventId, jpaPagingRequest)
                .map { GetPayHistoryResult.fromPayHistory(it) }.let {
                    PagingResult(
                        pageNo = it.number,
                        pageSize = it.size,
                        ttcn = it.totalElements,
                        nextPageExisYN = YN.of(it.hasNext()),
                        contents = it.content,
                    )
                }
        } ?: throw DamdaException(ErrorCode.EVEN_NOT_FOUND, "eventId ${request.eventId} not found")
    }
}