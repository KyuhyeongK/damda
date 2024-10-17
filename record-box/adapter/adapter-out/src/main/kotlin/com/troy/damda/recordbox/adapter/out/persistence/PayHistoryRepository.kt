package com.troy.damda.recordbox.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface PayHistoryRepository : JpaRepository<PayHistoryEntity, Long> {
}