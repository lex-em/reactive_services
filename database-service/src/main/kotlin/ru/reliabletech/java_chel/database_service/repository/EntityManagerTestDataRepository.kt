/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.repository

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import ru.reliabletech.java_chel.database_service.model.TestData
import javax.persistence.EntityManager

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 19.06.19
 */
@Repository
class EntityManagerTestDataRepository(private val entityManager: EntityManager) : TestDataRepository {

    override fun findTestData(data_like: String, pageable: Pageable): List<TestData> {
        val query = entityManager.createNamedStoredProcedureQuery("find")
            .setParameter(1, data_like)
            .setParameter(2, pageable.offset)
            .setParameter(3, pageable.pageSize)
        return query.getResultList() as List<TestData>
    }

}