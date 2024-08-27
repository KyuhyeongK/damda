plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.jetbrains.kotlin.plugin.jpa") version "2.0.20"
}

dependencies {
    implementation(project(":record-box:adapter:record-box-adapter-in"))
    implementation(project(":record-box:adapter:record-box-adapter-out"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")
}
