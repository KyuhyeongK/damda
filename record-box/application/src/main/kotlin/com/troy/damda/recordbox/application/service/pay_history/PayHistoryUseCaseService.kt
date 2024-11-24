package com.troy.damda.recordbox.application.service.pay_history

import com.troy.damda.YN
import com.troy.damda.auth.application.port.out.LoadUserPort
import com.troy.damda.recordbox.application.domain.PayHistory
import com.troy.damda.recordbox.application.domain.User
import com.troy.damda.recordbox.application.port.`in`.pay_history.CreatePayHistoryUseCase
import com.troy.damda.recordbox.application.port.`in`.pay_history.CreatePayHistoryUseCase.*
import com.troy.damda.recordbox.application.port.`in`.pay_history.DeletePayHistoryUseCase
import com.troy.damda.recordbox.application.port.`in`.pay_history.UpdatePayHistoryUseCase
import com.troy.damda.recordbox.application.port.`in`.pay_history.UpdatePayHistoryUseCase.*
import com.troy.damda.recordbox.application.port.out.pay_history.CreatePayHistoryPort
import com.troy.damda.recordbox.application.port.out.pay_history.DeletePayHistoryPort
import com.troy.damda.recordbox.application.port.out.pay_history.LoadPayHistoryPort
import com.troy.damda.recordbox.application.port.out.pay_history.UpdatePayHistoryPort
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
    private val deletePayHistoryPort: DeletePayHistoryPort,
) : CreatePayHistoryUseCase,
    UpdatePayHistoryUseCase,
    DeletePayHistoryUseCase {

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
        return loadPayHistoryPort.findById(request.payHistoryId)?.also {
            if (it.createdBy.id != userMgmtNo) {
                throw RuntimeException("허용되지 않은 사용자의 수정 요청")
            } else if (it.eventId != request.eventId) {
                throw RuntimeException("납부내역ID와 연결된 이벤트ID 불일치")
            }
        }?.let {
            it.update(request.type, request.payAmount)
            UpdatePayHistoryResponse.fromDomain(updatePayHistoryPort.update(it))
        } ?: throw RuntimeException("PayHistory with id ${request.payHistoryId} not found.")
    }

    override fun deletePayHistory(userMgmtNo: Long, eventId: Long, payHistoryId: Long) {
        loadPayHistoryPort.findById(payHistoryId)?.also {
            if (it.createdBy.id != userMgmtNo) {
                throw RuntimeException("허용되지 않은 사용자의 삭제 요청")
            }
        }?.let {
            it.delete()
            deletePayHistoryPort.delete(it)
        } ?: throw RuntimeException("PayHistory with id $payHistoryId not found.")
    }
}