package ru.mypet.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*
import io.ktor.server.response.*

fun Application.configureSecurity() {
    authentication {
        jwt {
            val config = HoconApplicationConfig(ConfigFactory.load())
            realm = config.property("ktor.security.jwt.realm").getString()

            val secret = config.property("ktor.security.jwt.secret").getString()
            val issuer = config.property("ktor.security.jwt.issuer").getString()
            val audience = config.property("ktor.security.jwt.audience").getString()
            val claim = config.property("ktor.security.jwt.claim").getString()

            val algorithm = Algorithm.HMAC512(secret)

            verifier(
                JWT
                    .require(algorithm)
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build()
            )
            validate { credential ->
                if (!credential.payload.getClaim(claim).asString().isNullOrEmpty()) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { _, _ ->
                call.response.headers.append(HttpHeaders.WWWAuthenticate, "Bearer")
                call.respond(status = HttpStatusCode.Unauthorized, "Unauthorized! Token is not valid or has expired")
            }
        }
    }
}
