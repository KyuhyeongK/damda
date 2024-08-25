package com.troy.recordbox.adapter.out

import org.springframework.data.jpa.repository.JpaRepository

interface EventJpaRepository : JpaRepository<EventJpaEntity, Long> {

    fun findAllByCreatedBy(userId: Long): List<EventJpaEntity>
}