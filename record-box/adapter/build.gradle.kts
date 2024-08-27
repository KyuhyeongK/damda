plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

subprojects {
    dependencies {
        implementation(project(":record-box:record-box-application"))
    }
}