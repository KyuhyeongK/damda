plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

subprojects {
    dependencies {
        implementation(project(":auth:auth-application"))
    }
}