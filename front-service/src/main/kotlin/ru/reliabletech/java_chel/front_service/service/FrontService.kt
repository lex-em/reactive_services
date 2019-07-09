package ru.reliabletech.java_chel.front_service.service

/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */

import ru.reliabletech.java_chel.front_service.dto.ComplexData

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 10.05.19
 */
interface FrontService {

    suspend fun getComplexData(): ComplexData
}