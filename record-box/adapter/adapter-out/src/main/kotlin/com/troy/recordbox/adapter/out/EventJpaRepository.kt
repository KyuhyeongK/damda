package com.troy.recordbox.adapter.out

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface EventJpaRepository : JpaRepository<EventJpaEntity, Long> {

    @Query(
        """
        select a
        from EventJpaEntity a
        where a.createdBy.id = :userId
    """
    )
    fun findAllByCreatedBy(@Param("userId") userId: Long): List<EventJpaEntity>
}