/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.service

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.reliabletech.java_chel.database_service.model.Alphabet16
import ru.reliabletech.java_chel.database_service.model.TestData
import ru.reliabletech.java_chel.database_service.repository.TestDataRepository

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 27.05.19
 */
@Service
@Transactional
class VerySlowlyTestDataService(private val testDataRepository: TestDataRepository) : TestDataService {

    override suspend fun fetchTestData(page: Int, size: Int): Flow<TestData> {
        return testDataRepository.findTestData(page, size, Alphabet16.random().name)
    }
}