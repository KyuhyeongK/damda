package com.troy.damda.recordbox.application.port.out

import com.troy.damda.recordbox.application.domain.Event
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate

interface LoadEventPort {
    fun findAllByCreatedBy(userMgmtNo: Long, iqryStartDate: LocalDate?, iqryEndDate: LocalDate?, pageable: Pageable): Page<Event>

    fun findById(id: Long): Event?
}