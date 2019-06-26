package ru.reliabletech.java_chel.front_service.service

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import ru.reliabletech.java_chel.front_service.dto.ComplexData
import ru.reliabletech.java_chel.front_service.dto.DelayedData
import ru.reliabletech.java_chel.front_service.dto.TestData

/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */


/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 10.05.19
 */
@Service
class GenericFrontService(private val restTemplate: RestTemplate) : FrontService {

    override fun getComplexData(): ComplexData {
        val delayedData = restTemplate.getForEntity("http://localhost:8081/delayed", DelayedData::class.java)
            .body!!
        val testData = restTemplate.exchange(
            "http://localhost:8082/slowly-data?page={page}&size={size}",
            HttpMethod.GET,
            HttpEntity.EMPTY,
            object : ParameterizedTypeReference<List<TestData>>() {},
            20, 200)
            .body!!

        return ComplexData(delayedData, testData)
    }

}