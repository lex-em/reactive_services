/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.model;

import java.security.SecureRandom

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 * Created on 19.06.19
 */
private val random = SecureRandom()

enum class Alphabet16 {
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P;

    companion object {

        fun random() : Alphabet16 {
            return values()[random.nextInt(16)]
        }
    }
}
