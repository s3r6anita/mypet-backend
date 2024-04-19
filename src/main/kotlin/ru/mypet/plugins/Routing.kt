package ru.mypet.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.mypet.routes.authRoutes

fun Application.configureRouting() {
    routing {
        authRoutes()
    }
}

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
//                    val tokens = tokenManager.generateToken(loginRequest.email)
//                    InMemoryCache.tokens.add(TokenPair(username = loginRequest.email, tokens = tokens))
//                    call.respond(hashMapOf("Token" to tokens))
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
//                    val tokens = tokenManager.generateToken(loginRequest.email)
//                    InMemoryCache.userList.add(loginRequest)
//                    InMemoryCache.tokens.add(TokenPair(username = loginRequest.email, tokens = tokens))
//                    call.respond(hashMapOf("Token" to tokens))
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
//                    val tokens = UUID.randomUUID().toString()
//                    InMemoryCache.tokens.add(TokenPair(login = receive.login,tokens = tokens))
//                    call.respond(LoginResponseRemote(tokens = tokens))
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
//            val tokens = UUID.randomUUID().toString()
//            InMemoryCache.userList.add(receive)
//            InMemoryCache.tokens.add(TokenPair(login = receive.login, tokens = tokens))
//
//            call.respond(RegisterResponseRemote(tokens = tokens))
//        }
//    }