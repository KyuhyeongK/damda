plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "damda"
include("record-box")
include("record-box:application")
findProject(":record-box:application")?.name = "record-box-application"
include("record-box:adapter")
findProject(":record-box:adapter")?.name = "record-box-adapter"
include("record-box:adapter:adapter-in")
findProject(":record-box:adapter:adapter-in")?.name = "adapter-in"
include("record-box:adapter:adapter-out")
findProject(":record-box:adapter:adapter-out")?.name = "adapter-out"
