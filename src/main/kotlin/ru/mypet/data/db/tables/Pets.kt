package ru.mypet.data.db.tables

import org.jetbrains.exposed.sql.Table

object Pets: Table("pets") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val name = text("name")
    val kind = text("kind")
    val breed = text("breed")
    val sex = text("sex")
    val birthday = text("birthday")
    val color = text("color")
    val coat = text("coat")
    val microchipNumber = text("microchip_number")
    val owner = varchar("owner", 30).references(Users.email)  // can be error there
}