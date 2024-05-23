package ru.mypet.models.medrecordParams

data class UpdateMedRecordParams(
    val id: Int,
    val title: String,
    val date: String,
    val notes: String,
    val pet: Int
)