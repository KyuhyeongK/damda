plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.jpa") version "2.0.20"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")
}
