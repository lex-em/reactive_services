/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.repository

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.asType
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import ru.reliabletech.java_chel.database_service.model.TestData

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 01.07.19
 */
@Repository
class ReactiveTestDataRepository(val databaseClient: DatabaseClient) : TestDataRepository {

    override fun findTestData(page: Int, size: Int, data_like: String): Flux<TestData> {
        return databaseClient.execute()
            .sql("select * from public.find(:data_like, :offset, :size)")
            .bind("data_like", data_like)
            .bind("offset", page*size)
            .bind("size", size)
            .asType<TestData>()
            .fetch()
            .all()
    }
}