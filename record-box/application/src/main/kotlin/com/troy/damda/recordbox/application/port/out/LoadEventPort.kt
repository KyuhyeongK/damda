package com.troy.damda.recordbox.application.port.out

import com.troy.damda.recordbox.application.domain.Event
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface LoadEventPort {
    fun findAllByCreatedBy(userMgmtNo: Long, pageable: Pageable): Page<Event>

    fun findById(id: Long): Event?
}