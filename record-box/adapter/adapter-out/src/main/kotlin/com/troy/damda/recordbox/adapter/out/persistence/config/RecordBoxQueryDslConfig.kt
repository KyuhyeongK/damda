package com.troy.damda.recordbox.adapter.out.persistence.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RecordBoxQueryDslConfig(
    private val em: EntityManager,
) {
    @Bean
    fun recordBoxJpaQueryFactory() = JPAQueryFactory(em)
}