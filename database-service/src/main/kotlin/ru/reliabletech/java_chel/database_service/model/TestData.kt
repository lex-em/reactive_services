/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 19.05.19
 */
class TestData(val data: String, @Id var id: Long? = null)