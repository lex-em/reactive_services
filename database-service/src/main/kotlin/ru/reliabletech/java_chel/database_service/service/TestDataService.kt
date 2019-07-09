/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.service

import kotlinx.coroutines.flow.Flow
import ru.reliabletech.java_chel.database_service.model.TestData

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 19.05.19
 */
interface TestDataService {

    suspend fun fetchTestData(page: Int, size: Int) : Flow<TestData>
}