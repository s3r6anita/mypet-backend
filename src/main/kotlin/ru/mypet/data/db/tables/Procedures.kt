package ru.mypet.data.db.tables

import org.jetbrains.exposed.sql.Table

object Procedures: Table("procedures") {
    val title = integer("title") // название
    val isDone = integer("isDone") // выполнена ли: 0 - нет, 1 - да
    val frequency = text("frequency") // раз в сколько часов повторять
    val frequencyOption = integer("frequencyOption") // ссылка на частоту
    val dateDone = text("dateDone") // когда следует выполнить
    val notes = text("notes") // заметки
    val reminder = text("reminder").nullable() // дата и время уведомления
    val inMedCard = integer("inMedCard") // нужно ли добавить в медкарту: 0 - нет, 1 - да
    val pet = integer("pet").references(Pets.id) // питомец
    val id = integer("id").autoIncrement().uniqueIndex()
}
