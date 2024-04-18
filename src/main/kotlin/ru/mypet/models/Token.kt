package ru.mypet.models

data class Token(
    val bearerToken: String,
    val refreshToken: String,
    val expirationTimeInMillis: Long
)