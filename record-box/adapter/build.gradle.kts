plugins {
    kotlin("jvm")
}

subprojects {
    dependencies {
        implementation(project(":record-box:record-box-application"))
    }
}