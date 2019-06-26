/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.model

import javax.persistence.*

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 19.05.19
 */
@Entity
@Table(name = "test_data")
@NamedStoredProcedureQueries(
    NamedStoredProcedureQuery(name = "find",
        procedureName = "public.find",
        resultClasses = [TestData::class],
        parameters = [StoredProcedureParameter(mode = ParameterMode.IN, type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, type = Int::class)])
)
class TestData(val data: String, @Id var id: Long? = null)