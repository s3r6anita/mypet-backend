package ru.mypet.models

data class CreateUserParams(
    val email: String,
    val password: String,
    val name: String
)
