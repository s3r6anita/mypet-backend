package ru.mypet.data.db

import org.jetbrains.exposed.sql.Table


data class CreateUserParams(
    val email: String,
    val password: String,
    val name: String
)

data class LoginUserParams(
    val email: String,
    val password: String
)

object Users: Table("users") {
    val email = varchar("email", 30)
    val password = text("password")
    val name = varchar("name", 30)
//    val authToken = varchar("authToken", 50)
//    val created_at = Users.datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey = PrimaryKey(email)
}