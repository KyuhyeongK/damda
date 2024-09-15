package com.troy.damda.recordbox.application.port.out

import com.troy.damda.recordbox.application.domain.Event

interface CreateEventPort {
    fun create(event: Event): Event
}