package ru.mypet.models.petParams

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