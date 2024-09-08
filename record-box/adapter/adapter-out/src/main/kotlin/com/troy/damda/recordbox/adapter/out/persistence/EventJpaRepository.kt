package com.troy.damda.recordbox.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface EventJpaRepository : JpaRepository<EventEntity, Long> {

    fun findAllByCreatedById(userMgmtNo: Long): List<EventEntity>
}