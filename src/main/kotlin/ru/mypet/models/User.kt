package ru.mypet.models

data class User(
    val email: String,
    val password: String?,
    val name: String,
    val vkid: Long?
//    val authToken: String? = null
)