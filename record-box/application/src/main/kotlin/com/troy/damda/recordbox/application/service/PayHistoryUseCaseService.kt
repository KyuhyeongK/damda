package com.troy.damda.recordbox.application.service

import com.troy.damda.YN
import com.troy.damda.auth.application.port.out.LoadUserPort
import com.troy.damda.recordbox.application.domain.PayHistory
import com.troy.damda.recordbox.application.domain.User
import com.troy.damda.recordbox.application.port.`in`.CreatePayHistoryUseCase
import com.troy.damda.recordbox.application.port.`in`.CreatePayHistoryUseCase.*
import com.troy.damda.recordbox.application.port.`in`.UpdatePayHistoryUseCase
import com.troy.damda.recordbox.application.port.`in`.UpdatePayHistoryUseCase.*
import com.troy.damda.recordbox.application.port.out.CreatePayHistoryPort
import com.troy.damda.recordbox.application.port.out.LoadPayHistoryPort
import com.troy.damda.recordbox.application.port.out.UpdatePayHistoryPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class PayHistoryUseCaseService(
    private val loadUserPort: LoadUserPort,
    private val createPayHistoryPort: CreatePayHistoryPort,
    private val loadPayHistoryPort: LoadPayHistoryPort,
    private val updatePayHistoryPort: UpdatePayHistoryPort,
) : CreatePayHistoryUseCase, UpdatePayHistoryUseCase {

    override fun createPayHistory(
        userMgmtNo: Long,
        request: CreatePayHistoryRequest,
    ): CreatePayHistoryResponse {
        return loadUserPort.findByUserMgmtNo(userMgmtNo)?.let {
            CreatePayHistoryResponse.fromDomain(
                createPayHistoryPort.create(
                    PayHistory(
                        type = request.type,
                        createdBy = User(it.nickname, userMgmtNo),
                        payAmount = request.payAmount,
                        eventId = request.eventId,
                        createdAt = LocalDateTime.now(),
                        deleteYN = YN.N
                    )
                )
            )
        } ?: throw RuntimeException("User with id $userMgmtNo not found.")
    }

    override fun updatePayHistory(
        userMgmtNo: Long,
        request: UpdatePayHistoryRequest
    ): UpdatePayHistoryResponse {
        return loadPayHistoryPort.findById(request.payHistoryId)?.let {
            require(it.createdBy.id == userMgmtNo) { "사관번호 불일치" }
            require(it.eventId == request.eventId) { "납부내역ID와 연결된 이벤트ID 불일치" }

            it.update(request.type, request.payAmount)
            UpdatePayHistoryResponse.fromDomain(updatePayHistoryPort.update(it))
        } ?: throw RuntimeException("PayHistory with id ${request.payHistoryId} not found.")
    }
}