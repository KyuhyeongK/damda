package com.troy.damda.recordbox.adapter.out.persistence

import com.troy.damda.YN
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<EventEntity, Long> {

    fun findAllByCreatedByIdAndDeleteYn(userMgmtNo: Long, deleteYn: YN, pageable: Pageable): Page<EventEntity>
}