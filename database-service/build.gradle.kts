import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val artifactId = "database-service"

apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin")
    compile("io.github.microutils:kotlin-logging:1.6.26")
    compile("com.zaxxer:HikariCP")
    compile("org.flywaydb:flyway-core:5.2.4")
    runtime("org.postgresql:postgresql")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions{
        jvmTarget = "11"
        apiVersion = "1.3"
        languageVersion = "1.3"
    }
}

