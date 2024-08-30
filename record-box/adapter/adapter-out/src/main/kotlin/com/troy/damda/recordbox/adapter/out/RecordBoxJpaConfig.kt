package com.troy.damda.recordbox.adapter.out

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.troy.damda.recordbox.adapter.out"])
class RecordBoxJpaConfig {
}