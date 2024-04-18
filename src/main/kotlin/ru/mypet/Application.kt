package ru.mypet

import io.ktor.server.cio.*
import io.ktor.server.engine.*
import ru.mypet.data.db.DatabaseFactory
import ru.mypet.plugins.configureRouting
import ru.mypet.security.configureSecurity
import ru.mypet.plugins.configureSerialization
import ru.mypet.routes.authRoutes
import ru.mypet.routes.petRoutes

fun main() {
    embeddedServer(
        CIO, port = 8080, host = "0.0.0.0") {
        DatabaseFactory.init()
        configureSerialization()
        configureSecurity()
        configureRouting()
        authRoutes()
        petRoutes()
    }.start(wait = true)
}