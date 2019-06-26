package ru.reliabletech.java_chel.front_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

fun main(vararg args: String) {
    runApplication<FrontService>(*args)
}

@SpringBootApplication
class FrontService