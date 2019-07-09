package ru.reliabletech.java_chel.front_service.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
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

    override suspend fun getComplexData(): ComplexData {
        val delayedData = CoroutineScope(Dispatchers.IO).async {
            delayedService.get()
                .retrieve()
                .awaitBody<DelayedData>()
        }
        val testData = CoroutineScope(Dispatchers.IO).async {
            databaseService.get()
                .uri {
                    it.queryParam("page", 20)
                        .queryParam("size", 200)
                        .build()
                }
                .retrieve()
                .awaitBody<List<TestData>>()
        }
        return ComplexData(delayedData.await(), testData.await())
    }
}