package ru.mypet.models

import java.time.LocalDateTime

data class Pet(
    val id: Int = 0,
    val name: String,
    val kind: String, // кошка собака морж
    val breed: String, // порода
    val sex: String, // "Самка" | "Самец"
    val birthday: LocalDateTime,
    val color: String, // окрас
    val coat: String, // вид шерсти
    val microchipNumber: String, // 15 цифр
)