/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.front_service.dto

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 26.06.19
 */
data class ComplexData(val delayed: DelayedData, val testData: List<TestData>)