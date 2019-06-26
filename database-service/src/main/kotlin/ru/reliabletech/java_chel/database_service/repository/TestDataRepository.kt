/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.repository

import org.springframework.data.domain.Pageable
import ru.reliabletech.java_chel.database_service.model.TestData

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 19.05.19
 */
interface TestDataRepository { //: JpaRepository<TestData, Long> {

    fun findTestData(data_like: String, pageable: Pageable) : List<TestData>
}