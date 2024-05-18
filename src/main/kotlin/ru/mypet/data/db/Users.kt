package ru.mypet.data.db

import org.jetbrains.exposed.sql.Table

object Users: Table("users") {
    val email = varchar("email", 30)
    val password = text("password").nullable()
    val name = varchar("name", 30)
    val vkid = long("vkid").nullable()

//    val authToken = varchar("authToken", 50)
//    val created_at = Users.datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey = PrimaryKey(email)
}