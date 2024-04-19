package ru.mypet.data.db

import org.jetbrains.exposed.sql.Table

data class CreatePetParams(
    val name: String,
    val kind: String, // кошка собака морж
    val breed: String, // порода
    val sex: String, // "Самка" | "Самец"
    val birthday: String,                     // can be error there
    val color: String, // окрас
    val coat: String, // вид шерсти
    val microchipNumber: String, // 15 цифр
    val owner: String? = null
) {
    fun setOwner(owner: String): CreatePetParams = this.copy(owner = owner)
}

object Pets: Table("pets") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val name = text("name")
    val kind = text("kind")
    val breed = text("breed")
    val sex = text("sex")
    val birthday = text("birthday")
    val color = text("color")
    val coat = text("coat")
    val microchipNumber = text("microchip_number")
    val owner = varchar("owner", 30).references(Users.email)  // can be error there
}