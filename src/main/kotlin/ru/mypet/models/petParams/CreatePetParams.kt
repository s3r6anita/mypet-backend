package ru.mypet.models.petParams

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