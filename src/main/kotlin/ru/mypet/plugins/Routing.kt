package ru.mypet.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.mypet.cache.InMemoryCache
import ru.mypet.models.LoginRequest
import ru.mypet.models.RegisterRequest
import ru.mypet.models.TokenCache
import ru.mypet.utils.isValidEmail

fun Application.configureRouting() {

//    routing {
//        authenticate {
//            get("/authHello") {
//                call.respondText("Authenticated Hello World!")
//            }
//        }
//        get("/") {
//            call.respondText("Hello World!")
//        }
//    }

//    routing {
//        val tokenManager = TokenManager
//        post("/login") {
//            val loginRequest = call.receive<LoginRequest>()
//
////            val user = Users.getUserByUsername(loginRequest.username)
//            val user = InMemoryCache.userList.firstOrNull { it.email == loginRequest.email }
//
//            if (user != null) {
//                if (loginRequest.password == user.password) {
//                    val token = tokenManager.generateToken(loginRequest.email)
//                    InMemoryCache.token.add(TokenCache(username = loginRequest.email, token = token))
//                    call.respond(hashMapOf("Token" to token))
//                } else {
//                    call.respond(HttpStatusCode.BadRequest, "Invalid password")
//                }
//            } else {
//                call.respond(HttpStatusCode.BadRequest, "Invalid login")
//            }
//        }
//
//        post("/register") {
//            val loginRequest = call.receive<RegisterRequest>()
//
//            if (!loginRequest.email.isValidEmail()) {
//                call.respond(HttpStatusCode.BadRequest, "Invalid email")
//            } else {
//                if (InMemoryCache.userList.map { it.email }.contains(loginRequest.email)) {
//                    call.respond(HttpStatusCode.Conflict, "User already exists")
//                } else {
//                    val token = tokenManager.generateToken(loginRequest.email)
//                    InMemoryCache.userList.add(loginRequest)
//                    InMemoryCache.token.add(TokenCache(username = loginRequest.email, token = token))
//                    call.respond(hashMapOf("Token" to token))
//                }
//            }
//        }
//    }

    // loginRouting
//    routing {
//        post("/login") {
//            val receive = call.receive<LoginReceiveRemote>()
//            val user = InMemoryCache.userList.firstOrNull { it.login == receive.login }
//
//            if (user == null) {
//                call.respond(HttpStatusCode.BadRequest, "Invalid login")
//            } else {
//                if (user.password == receive.password) {
//                    val token = UUID.randomUUID().toString()
//                    InMemoryCache.token.add(TokenCache(login = receive.login,token = token))
//                    call.respond(LoginResponseRemote(token = token))
//                } else
//                    call.respond(HttpStatusCode.BadRequest, "Invalid password")
//            }
//        }
//    }

    // registerRouting
//    routing {
//        post("/register") {
//            val receive = call.receive<RegisterReceiveRemote>()
//            if (!receive.email.isValidEmail()) {
//                call.respond(HttpStatusCode.BadRequest, "Invalid email")
//            }
//
//            if (InMemoryCache.userList.map { it.login }.contains(receive.login)) {
//                call.respond(HttpStatusCode.Conflict, "User already exists")
//            }
//            val token = UUID.randomUUID().toString()
//            InMemoryCache.userList.add(receive)
//            InMemoryCache.token.add(TokenCache(login = receive.login, token = token))
//
//            call.respond(RegisterResponseRemote(token = token))
//        }
//    }
}
