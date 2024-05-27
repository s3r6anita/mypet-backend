package ru.mypet.data.db.tables

import org.jetbrains.exposed.sql.Table

object ProcedureTitles : Table("procedure_titles") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val name = text("name")
    val type = integer("type")
}
