package com.troy.damda.recordbox.application.domain

import com.troy.damda.auth.application.port.out.LoadUserPort
import com.troy.damda.recordbox.application.port.`in`.CreateEventUseCase
import com.troy.damda.recordbox.application.port.out.CreateEventPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateEventUseCaseService(
    private val createEventPort: CreateEventPort,
    private val loadUserPort: LoadUserPort,
) : CreateEventUseCase {
    override fun createEvent(
        userMgmtNo: Long, request: CreateEventUseCase.CreateEventRequest
    ): CreateEventUseCase.CreateEventResponse {
        return loadUserPort.findByUserMgmtNo(userMgmtNo)?.let {
            CreateEventUseCase.CreateEventResponse.fromDomain(
                createEventPort.create(
                    Event(
                        request.eventName,
                        request.type,
                        User(it.nickname, userMgmtNo),
                        request.owner,
                        request.relationship,
                        request.eventDate,
                    )
                )
            )
        } ?: throw RuntimeException("User with id $userMgmtNo not found.")

    }
}