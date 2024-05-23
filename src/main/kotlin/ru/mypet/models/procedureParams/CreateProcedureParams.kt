package ru.mypet.models.procedureParams

data class CreateProcedureParams( // without "id"
    val title: Int,
    val isDone: Int,
    val frequency: String,
    val frequencyOption: Int,
    val dateDone: String,
    val notes: String,
    val reminder: String?,
    val pet: Int,
    val inMedCard: Int
)