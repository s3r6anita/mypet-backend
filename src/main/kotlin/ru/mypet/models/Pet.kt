package ru.mypet.models

import java.time.LocalDate

data class Pet(
    val id: Int = 0,
    val name: String,
    val kind: String, // кошка собака морж
    val breed: String, // порода
    val sex: String, // "Самка" | "Самец"
    val birthday: LocalDate,
    val color: String, // окрас
    val coat: String, // вид шерсти
    val microchipNumber: String, // 15 цифр
    val owner: String
)