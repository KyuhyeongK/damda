package com.troy.damda.recordbox.application.port.out

import com.troy.damda.recordbox.application.domain.Event

interface EventRepositoryPort {
    fun findAllByCreatedBy(userId: Long): List<Event>

}