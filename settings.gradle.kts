rootProject.name = "reactive_services"
rootProject.buildFileName = "build.gradle.kts"
include("front-service", "delay-service", "database-service")

val kotlin_version: String by settings

pluginManagement {
    repositories {
        maven("https://repo.spring.io/milestone")
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            val id = requested.id.id
            if (id.startsWith("org.jetbrains.kotlin")) {
                useVersion(kotlin_version)
            }
            if (id.startsWith("org.springframework.boot")) {
                useModule("org.springframework.boot:spring-boot-gradle-plugin:2.2.0.M3")
            }
        }
    }
}