package ru.mypet.models.medrecordParams

data class CreateMedRecordParams(
    val title: String,
    val date: String,
    val notes: String,
    val pet: Int
)