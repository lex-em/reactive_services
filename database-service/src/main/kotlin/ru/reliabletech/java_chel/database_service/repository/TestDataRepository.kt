/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.repository

import kotlinx.coroutines.flow.Flow
import ru.reliabletech.java_chel.database_service.model.TestData

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 19.05.19
 */
interface TestDataRepository {

    suspend fun findTestData(page: Int, size: Int, data_like: String) : Flow<TestData>
}