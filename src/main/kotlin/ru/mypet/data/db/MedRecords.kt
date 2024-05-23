package ru.mypet.data.db

import org.jetbrains.exposed.sql.Table

data class CreateMedRecordParams(
    val title: String,
    val date: String,
    val notes: String,
    val pet: Int
)

data class UpdateMedRecordParams(
    val id: Int,
    val title: String,
    val date: String,
    val notes: String,
    val pet: Int
)

object MedRecords: Table("medrecords") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val title = text("title")
    val date = text("date")
    val notes = text("notes")
    val pet = integer("pet").references(Pets.id)
}

