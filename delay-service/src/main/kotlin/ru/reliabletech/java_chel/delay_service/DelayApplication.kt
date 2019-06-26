package ru.reliabletech.java_chel.delay_service

import kotlinx.coroutines.delay
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

fun main(vararg args: String) {
    runApplication<DelayApplication>(*args)
}

@SpringBootApplication
class DelayApplication

@RestController
class DelayController {

    @GetMapping("/delayed")
    suspend fun delayeData() : DelayedData {
        delay(3000)
        return DelayedData("delayed")
    }
}

data class DelayedData(val delayString: String)