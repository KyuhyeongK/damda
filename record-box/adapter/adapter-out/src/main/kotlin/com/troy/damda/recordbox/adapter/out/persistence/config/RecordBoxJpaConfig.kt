package com.troy.damda.recordbox.adapter.out.persistence.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.troy.damda.recordbox.adapter.out"])
@EntityScan(basePackages = ["com.troy.damda.recordbox.adapter.out"])
class RecordBoxJpaConfig(
    private val em: EntityManager,
) {

    @Bean
    fun recordBoxJpaQueryFactory() = JPAQueryFactory(em)
}