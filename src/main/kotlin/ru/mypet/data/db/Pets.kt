package ru.mypet.data.db

import org.jetbrains.exposed.sql.Table

data class CreatePetParams(
    val name: String,
    val kind: String,
    val breed: String,
    val sex: String,
    val birthday: String,
    val color: String,
    val coat: String,
    val microchipNumber: String,
    val owner: String? = null
) {
    fun setOwner(owner: String): CreatePetParams = this.copy(owner = owner)
}

data class UpdatePetParams(
    val id: Int,
    val name: String,
    val kind: String,
    val breed: String,
    val sex: String,
    val birthday: String,
    val color: String,
    val coat: String,
    val microchipNumber: String
)

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