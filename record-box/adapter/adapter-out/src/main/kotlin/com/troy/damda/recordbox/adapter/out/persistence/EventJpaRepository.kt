package com.troy.damda.recordbox.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface EventJpaRepository : JpaRepository<EventJpaEntity, Long> {

    @Query(
        """
        select a
        from EventJpaEntity a
        where a.createdBy.id = :userMgmtNo
    """
    )
    fun findAllByCreatedBy(@Param("userMgmtNo") userMgmtNo: Long): List<EventJpaEntity>
}