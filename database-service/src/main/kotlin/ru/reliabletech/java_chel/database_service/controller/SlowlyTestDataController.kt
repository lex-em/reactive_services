/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import ru.reliabletech.java_chel.database_service.model.TestData
import ru.reliabletech.java_chel.database_service.service.VerySlowlyTestDataService

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 27.05.19
 */
@RestController
class SlowlyTestDataController (private val slowlyTestDataService: VerySlowlyTestDataService) {

    @GetMapping("/slowly-data")
    fun getSlowData(@RequestParam page: Int, @RequestParam size: Int) : Flux<TestData> {
        return slowlyTestDataService.fetchTestData(page, size)
    }

}