package ru.mypet.data.db.tables

import org.jetbrains.exposed.sql.Table

object MedRecords: Table("medrecords") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val title = text("title")
    val date = text("date")
    val notes = text("notes")
    val pet = integer("pet").references(Pets.id)
}
