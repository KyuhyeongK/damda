package com.troy.recordbox.application.port.out

import com.troy.recordbox.application.domain.Event

interface EventRepositoryPort {
    fun findAllByCreatedBy(userId: Long): List<Event>

}