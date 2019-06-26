plugins {
    kotlin("jvm") apply false
    kotlin("plugin.spring")  apply false
    kotlin("plugin.noarg")  apply false
    id("io.spring.dependency-management") version "1.0.7.RELEASE"  apply false
    id("org.springframework.boot")  apply false
}

subprojects {
    group = "ru.reliabletech.java_chel"
    version = "0.1.0-SNAPSHOT"

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.noarg")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    repositories {
        maven("https://repo.spring.io/milestone")
        mavenCentral()
    }
}
