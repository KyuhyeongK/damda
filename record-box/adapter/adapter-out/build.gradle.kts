plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.jetbrains.kotlin.plugin.jpa") version "2.0.20"
    kotlin("kapt")
}

dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")
    implementation("com.querydsl:querydsl-apt:5.1.0:jakarta")
    implementation("jakarta.persistence:jakarta.persistence-api")
    implementation("jakarta.annotation:jakarta.annotation-api")
    kapt("com.querydsl:querydsl-apt:5.1.0:jakarta")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    runtimeOnly("com.mysql:mysql-connector-j")
}
