package com.troy.damda.recordbox.adapter.out.persistence

import com.troy.damda.YN
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<EventEntity, Long> {

    fun findAllByCreatedByIdAndDeleteYn(userMgmtNo: Long, deleteYn: YN): List<EventEntity>
}