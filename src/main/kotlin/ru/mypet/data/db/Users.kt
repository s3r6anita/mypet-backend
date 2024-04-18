package ru.mypet.data.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

data class User(
    val email: String,
    val password: String,
    val name: String,
//    val authToken: String? = null
)

data class CreateUserParams(
    val email: String,
    val password: String,
    val name: String
)

object Users: Table("users") {
    val email = varchar("email", 30)
    val password = text("password")
    val name = varchar("name", 30)
//    val authToken = varchar("authToken", 50)
//    val created_at = Users.datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey = PrimaryKey(email)
}