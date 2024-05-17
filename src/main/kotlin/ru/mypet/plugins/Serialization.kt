package ru.mypet.plugins

import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import ru.mypet.utils.LocalDateAdapter
import ru.mypet.utils.LocalDateTimeAdapter
import java.time.LocalDate
import java.time.LocalDateTime

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
            registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        }
    }
}
