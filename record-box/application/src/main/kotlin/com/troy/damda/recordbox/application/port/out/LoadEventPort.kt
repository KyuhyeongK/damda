package com.troy.damda.recordbox.application.port.out

import com.troy.damda.recordbox.application.domain.Event

interface LoadEventPort {
    fun findAllByCreatedBy(userMgmtNo: Long): List<Event>

    fun findById(id: Long): Event?
}