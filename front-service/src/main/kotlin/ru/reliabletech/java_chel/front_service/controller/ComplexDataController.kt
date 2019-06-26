package ru.reliabletech.java_chel.front_service.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.reliabletech.java_chel.front_service.dto.ComplexData
import ru.reliabletech.java_chel.front_service.service.FrontService

/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */


/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 10.05.19
 */
@RestController
class ComplexDataController(private val frontService: FrontService) {

    @GetMapping("/data")
    fun delayeData() : ComplexData {
        return frontService.getComplexData()
    }
}