/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.service

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.reliabletech.java_chel.database_service.model.Alphabet16
import ru.reliabletech.java_chel.database_service.model.TestData
import ru.reliabletech.java_chel.database_service.repository.TestDataRepository
import javax.transaction.Transactional

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 27.05.19
 */
@Service
@Transactional
class VerySlowlyTestDataService(private val testDataRepository: TestDataRepository) :TestDataService {

    override fun fetchTestData(pageable: Pageable) : List<TestData> {
        return testDataRepository.findTestData(Alphabet16.random().name, pageable)
    }
}