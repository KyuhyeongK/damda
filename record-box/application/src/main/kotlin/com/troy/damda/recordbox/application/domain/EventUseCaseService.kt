package com.troy.damda.recordbox.application.domain

import com.troy.damda.auth.application.port.out.LoadUserPort
import com.troy.damda.recordbox.application.port.`in`.CreateEventUseCase
import com.troy.damda.recordbox.application.port.`in`.CreateEventUseCase.*
import com.troy.damda.recordbox.application.port.`in`.CreateEventUseCase.CreateEventResponse.*
import com.troy.damda.recordbox.application.port.`in`.UpdateEventUseCase
import com.troy.damda.recordbox.application.port.`in`.UpdateEventUseCase.*
import com.troy.damda.recordbox.application.port.out.CreateEventPort
import com.troy.damda.recordbox.application.port.out.LoadEventPort
import com.troy.damda.recordbox.application.port.out.UpdateEventPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class EventUseCaseService(
    private val createEventPort: CreateEventPort,
    private val loadEventPort: LoadEventPort,
    private val updateEventPort: UpdateEventPort,
    private val loadUserPort: LoadUserPort,
) : CreateEventUseCase, UpdateEventUseCase {
    override fun createEvent(
        userMgmtNo: Long, request: CreateEventRequest
    ): CreateEventResponse {
        return loadUserPort.findByUserMgmtNo(userMgmtNo)?.let {
            CreateEventResponse.fromDomain(
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

    override fun updateEvent(
        userMgmtNo: Long, eventId: Long, request: UpdateEventRequest
    ): UpdateEventResponse {

        return loadEventPort.findById(eventId)?.let {
            it.update(
                userMgmtNo, request.eventName, request.type, request.owner, request.relationship, request.eventDate
            )
            UpdateEventResponse.fromDomain(
                updateEventPort.update(it)
            )
        } ?: throw RuntimeException("Event with id $eventId not found.")
    }

}