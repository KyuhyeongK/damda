plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "damda"
include("record-box:application")
include("record-box:adapter:adapter-in")
include("record-box:adapter:adapter-out")
project(":record-box:application").name = "record-box-application"
project(":record-box:adapter:adapter-in").name = "record-box-adapter-in"
project(":record-box:adapter:adapter-out").name = "record-box-adapter-out"
