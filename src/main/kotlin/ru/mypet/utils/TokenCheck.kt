package ru.mypet.utils

object TokenCheck {

//    fun isTokenValid(tokens: String): Boolean =
//        Tokens.fetchTokens().firstOrNull { it.tokens == tokens } != null

    fun isTokenAdmin(token: String): Boolean =
        token == "bf8487ae-7d47-11ec-90d6-0242ac120003"
}