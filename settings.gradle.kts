rootProject.name = "ImageCaching"
//include(":composeApp")
//include(":imageCaching")
include("composeApp", "imageCaching")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
