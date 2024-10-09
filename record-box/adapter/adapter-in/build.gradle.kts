plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":auth:auth-application"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}