import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val artifactId = "delay-service"


repositories {
    maven (url = "http://oss.jfrog.org/artifactory/oss-snapshot-local/" )
}

dependencies {
    val coroutinesVersion = "1.2.1"
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutinesVersion")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutinesVersion")
    compile("org.springframework.boot:spring-boot-starter-webflux")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin")
    compile("io.github.microutils:kotlin-logging:1.6.26")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions{
        jvmTarget = "11"
        apiVersion = "1.3"
        languageVersion = "1.3"
    }
}

