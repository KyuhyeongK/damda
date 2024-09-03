package com.troy.damda

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.troy"])
class DamdaApplication

fun main(args: Array<String>) {
    runApplication<DamdaApplication>(*args)
}