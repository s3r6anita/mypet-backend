package ru.mypet.security

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {
    JwtConfig.initialize()
    install(Authentication) {
        jwt {
            verifier(JwtConfig.instance.verifier)
            realm = JwtConfig.realm

            validate { credential ->
                val claim = credential.payload.getClaim(JwtConfig.claim).asString()
                if (claim != null) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }

//            challenge { _, _ ->
//                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
//            }
        }
    }
}