package ru.mypet.models

data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String
)

//data class RegisterResponseRemote(
//    val tokens: String
//)