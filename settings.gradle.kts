plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "damda"

// record-box 모듈
include("record-box:application")
include("record-box:adapter:adapter-in")
include("record-box:adapter:adapter-out")
project(":record-box:application").name = "record-box-application"
project(":record-box:adapter:adapter-in").name = "record-box-adapter-in"
project(":record-box:adapter:adapter-out").name = "record-box-adapter-out"

// bootstrap 모듈
include("bootstrap")

// common 모듈
include("common")

// auth 모듈
include("auth:application")
include("auth:adapter:adapter-in")
include("auth:adapter:adapter-out")
project(":auth:application").name = "auth-application"
project(":auth:adapter:adapter-in").name = "auth-adapter-in"
project(":auth:adapter:adapter-out").name = "auth-adapter-out"
