package ru.reliabletech.java_chel.front_service.service

import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
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
class GenericFrontService : FrontService {

    private val delayedService = WebClient.create("http://localhost:8081/delayed")
    private val databaseService = WebClient.create("http://localhost:8082/slowly-data")

    override fun getComplexData(): Mono<ComplexData> {
        val delayedData = delayedService.get()
            .retrieve()
            .bodyToMono(DelayedData::class.java)
        val testData = databaseService.get()
            .uri {
                it.queryParam("page", 20)
                    .queryParam("size", 200)
                    .build()
            }
            .retrieve()
            .bodyToMono(object : ParameterizedTypeReference<List<TestData>>() {})
        return delayedData.zipWith(testData)
            .map { ComplexData(it.t1, it.t2) }
    }

}