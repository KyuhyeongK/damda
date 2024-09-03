package com.troy.damda

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.troy"])
//@EntityScan(basePackages = ["com.troy"])
class DamdaApplication

fun main(args: Array<String>) {
    runApplication<DamdaApplication>(*args)
}