package com.troy.damda.bootstrap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["com.troy"])
@EntityScan(basePackages = ["com.troy"])
@EnableJpaRepositories(basePackages = ["com.troy"])
class DamdaApplication

fun main(args: Array<String>) {
    runApplication<DamdaApplication>(*args)
}