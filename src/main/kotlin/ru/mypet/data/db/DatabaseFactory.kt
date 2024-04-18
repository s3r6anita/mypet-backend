package ru.mypet.data.db

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init() {
        Database.connect(createHikariDataSource())
        transaction {
            SchemaUtils.create(Users)
        }
    }

    private val config = HoconApplicationConfig(ConfigFactory.load())

    private fun createHikariDataSource() =
        HikariDataSource(HikariConfig().apply {
            driverClassName = config.property("ktor.storage.driverClassName").getString()
            jdbcUrl = config.property("ktor.storage.jdbcURL").getString()
            username = config.property("ktor.storage.user").getString()
            password = config.property("ktor.storage.password").getString()
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        })

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
