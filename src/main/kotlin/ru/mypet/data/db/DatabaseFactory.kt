package ru.mypet.data.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import ru.mypet.data.db.tables.Procedures
import ru.mypet.data.db.tables.Users

object DatabaseFactory {

    fun init(config: HoconApplicationConfig) {
        Database.connect(createHikariDataSource(config))
        transaction {
            SchemaUtils.create(Users)
            SchemaUtils.create(Procedures)
        }
    }

    private fun createHikariDataSource(config: HoconApplicationConfig) =
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
