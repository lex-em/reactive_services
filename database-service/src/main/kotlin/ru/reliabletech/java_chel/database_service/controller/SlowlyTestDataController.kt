/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.controller

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.reliabletech.java_chel.database_service.model.TestData
import ru.reliabletech.java_chel.database_service.service.VerySlowlyTestDataService

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 27.05.19
 */
@RestController
class SlowlyTestDataController (private val slowlyTestDataService: VerySlowlyTestDataService) {

    @GetMapping("/slowly-data")
    fun getSlowData(pageable: Pageable) : List<TestData> {
        return slowlyTestDataService.fetchTestData(pageable)
    }

}