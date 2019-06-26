package ru.reliabletech.java_chel.database_service

import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.sql.DriverManager

/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */


/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 19.05.19
 */
@SpringBootApplication
class DatabaseService

fun main(vararg args: String) {
    runApplication<DatabaseService>(*args)
}

@Component
class CommonDatabaseCreator(
    @Value("\${spring.datasource.url}")
    private val url: String,
    @Value("\${spring.datasource.username}")
    private val username: String,
    @Value("\${spring.datasource.password}")
    private val password: String
) {

    companion object : KLogging()


    private val databaseUrl: String
        get() = url.substring(0, url.lastIndexOf('/') + 1)

    private val databaseName: String
        get() = url.substring(url.lastIndexOf('/') + 1)

    fun createDatabase() {
        try {
            DriverManager.getConnection(databaseUrl, username, password).use { connection ->
                connection.createStatement().use { statement ->
                    statement.executeQuery(
                        """SELECT count(*) FROM pg_database 
                            WHERE datistemplate = false AND datname = '$databaseName'"""
                    ).use { resultSet ->
                        if (resultSet.next()) {
                            val result = resultSet.getInt(1)
                            if (result < 1) {
                                logger.info("Database '{}' does not exist! Creating...", databaseName)
                                statement.executeUpdate("CREATE DATABASE $databaseName")
                                logger.info("Database '{}' successfully created", databaseName)
                            } else {
                                logger.info("Database '{}' already exists", databaseName)
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            logger.error("Failed to create database \'{}\': {}", databaseName, e.message)
        }

    }
}

@Configuration
class DbConfig {
    @Bean
    fun beforeMigrationStrategy(databaseCreator: CommonDatabaseCreator): FlywayMigrationStrategy {
        return FlywayMigrationStrategy { flyway ->
            databaseCreator.createDatabase()
            flyway.migrate()
        }
    }
}
