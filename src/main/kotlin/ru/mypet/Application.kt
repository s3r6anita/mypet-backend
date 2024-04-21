package ru.mypet

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.mypet.data.db.DatabaseFactory
import ru.mypet.plugins.configureRouting
import ru.mypet.plugins.configureSerialization
import ru.mypet.security.configureSecurity

fun main() {
    val config = HoconApplicationConfig(ConfigFactory.load())
    val port = config.property("ktor.deployment.port").getString()
    embeddedServer(Netty, port = port.toInt()) {
        DatabaseFactory.init(config)
        configureSerialization()
        configureSecurity()
        configureRouting()
    }.start(wait = true)
}