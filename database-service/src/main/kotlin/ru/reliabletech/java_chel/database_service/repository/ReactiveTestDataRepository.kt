/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.repository

import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.asType
import org.springframework.data.r2dbc.core.flow
import org.springframework.stereotype.Repository
import ru.reliabletech.java_chel.database_service.model.TestData

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 01.07.19
 */
@Repository
class ReactiveTestDataRepository(val databaseClient: DatabaseClient) : TestDataRepository {

    override suspend fun findTestData(page: Int, size: Int, data_like: String): Flow<TestData> {
        return databaseClient.execute()
            .sql("select * from public.find(:data_like, :offset, :size)")
            .bind("data_like", data_like)
            .bind("offset", page*size)
            .bind("size", size)
            .asType<TestData>()
            .fetch()
            .flow()
    }
}