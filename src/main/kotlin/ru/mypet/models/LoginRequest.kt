package ru.mypet.models

data class LoginRequest(
    val email: String,
    val password: String
)

//data class LoginResponse(
//    val tokens: String
//)