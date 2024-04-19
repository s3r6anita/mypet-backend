package ru.mypet.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.mypet.data.db.CreateUserParams
import ru.mypet.data.db.LoginUserParams
import ru.mypet.models.TokenPair
import ru.mypet.repository.UserRepository
import ru.mypet.repository.UserRepositoryImpl

fun Route.authRoutes(
    repository: UserRepository = UserRepositoryImpl()
) {
    post("/register") {
        val params = call.receive<CreateUserParams>()
        val result = repository.registerUser(params)
        call.respond(result.statusCode, result)
    }
    post("/login") {
        val params = call.receive<LoginUserParams>()
        val result = repository.loginUser(params)
        call.respond(result.statusCode, result)
    }
    authenticate {
        get("/refreshToken") {
            val params = call.receive<TokenPair>()
            val result = repository.refreshTokenUser(params)
            call.respond(result.statusCode, result)
        }
    }
}