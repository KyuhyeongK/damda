package com.troy.damda.recordbox.adapter.out.persistence.pay_history

import org.springframework.data.jpa.repository.JpaRepository

interface PayHistoryRepository : JpaRepository<PayHistoryEntity, Long> {
}