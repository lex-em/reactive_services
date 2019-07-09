/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.config

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import ru.reliabletech.java_chel.database_service.CommonDatabaseCreator
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import org.springframework.core.env.Environment
import ru.reliabletech.java_chel.database_service.repository.TestDataRepository

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 01.07.19
 */
@EnableR2dbcRepositories(basePackageClasses = [TestDataRepository::class])
@Configuration
class DbConfig(private val env: Environment) : AbstractR2dbcConfiguration() {

    override fun connectionFactory(): ConnectionFactory {
        return PostgresqlConnectionFactory(
            PostgresqlConnectionConfiguration.builder()
                .host("localhost")
                .port(5432)
                .username("postgres")
                .password("postgres")
                .database("reactive_services")
                .build())
    }

    @Bean
    fun beforeMigrationStrategy(databaseCreator: CommonDatabaseCreator): FlywayMigrationStrategy {
        return FlywayMigrationStrategy { flyway ->
            databaseCreator.createDatabase()
            flyway.migrate()
        }
    }


}