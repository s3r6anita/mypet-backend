package ru.mypet.models.userParams

data class CreateUserParams(
    val email: String,
    val password: String?,
    val name: String,
    val vkid: Long?
)
