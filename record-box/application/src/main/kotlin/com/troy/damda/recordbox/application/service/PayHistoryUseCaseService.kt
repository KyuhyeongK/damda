package com.troy.damda.recordbox.application.service

import com.troy.damda.YN
import com.troy.damda.auth.application.port.out.LoadUserPort
import com.troy.damda.recordbox.application.domain.PayHistory
import com.troy.damda.recordbox.application.domain.User
import com.troy.damda.recordbox.application.port.`in`.CreatePayHistoryUseCase
import com.troy.damda.recordbox.application.port.`in`.CreatePayHistoryUseCase.*
import com.troy.damda.recordbox.application.port.out.CreatePayHistoryPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class PayHistoryUseCaseService(
    private val loadUserPort: LoadUserPort,
    private val createPayHistoryPort: CreatePayHistoryPort,
    ) : CreatePayHistoryUseCase {

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
}