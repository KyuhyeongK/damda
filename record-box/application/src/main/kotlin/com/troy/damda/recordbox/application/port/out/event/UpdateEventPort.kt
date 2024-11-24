package com.troy.damda.recordbox.application.port.out.event

import com.troy.damda.recordbox.application.domain.Event

interface UpdateEventPort {
    fun update(event: Event): Event
}