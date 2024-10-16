import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.jetbrains.kotlin.plugin.jpa") version "2.0.20"
}

dependencies {
    implementation(project(":common"))
    implementation(project(":record-box:adapter:record-box-adapter-in"))
    implementation(project(":record-box:adapter:record-box-adapter-out"))
    implementation(project(":auth:adapter:auth-adapter-in"))
    implementation(project(":auth:adapter:auth-adapter-out"))
}

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}

tasks.getByName<Jar>("jar") {
    enabled = false
}