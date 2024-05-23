package ru.mypet.models

import java.time.LocalDateTime

data class MedRecord(
    val title: String, // название
    val date: LocalDateTime, // дата
    val notes: String, // заметки
    val pet: Int, // питомец
    val id: Int
)