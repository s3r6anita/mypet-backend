package ru.mypet.models

import java.time.LocalDateTime

data class Procedure(
    val title: Int, // название
    val isDone: Int, // выполнена ли: 0 - нет, 1 - да
    val frequency: String, // период частоты
    val frequencyOption: Int, // ссылка на частоту
    val dateDone: LocalDateTime, // когда следует выполнить
    val notes: String, // заметки
    val reminder: LocalDateTime?, // дата и время уведомления
    val pet: Int, // питомец
    val inMedCard: Int, // нужно ли добавить в медкарту: 0 - нет, 1 - да
    val id: Int = 0
)
