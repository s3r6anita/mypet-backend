package ru.mypet.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.text.get

fun Route.petRoutes() {
    authenticate {
        route("/pet") {
            get {
                call.respondText("Hello!")

//                    val principal = call.principal<JWTPrincipal>()
//                    val email = principal!!.payload.getClaim("email").asString()
//                    val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
//                    call.respondText("Hello, $email! Token is expired at $expiresAt ms.")

//            if (customerStorage.isNotEmpty()) {
//                call.respond(customerStorage)
//            } else {
//                call.respondText("No customers found", status = HttpStatusCode.OK)
//            }
            }
//                get("{id?}") {
//
//                }
//                post {
//
//                }
//                delete("{id?}") {
//
//                }
        }
    }
}