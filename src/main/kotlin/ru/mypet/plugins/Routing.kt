package ru.mypet.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.mypet.routes.authRoutes

fun Application.configureRouting() {
    routing {
        authRoutes()
    }
}
