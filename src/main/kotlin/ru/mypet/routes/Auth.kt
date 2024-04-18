package ru.mypet.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.mypet.data.db.CreateUserParams
import ru.mypet.repository.UserRepository
import ru.mypet.repository.UserRepositoryImpl

fun Application.authRoutes(
    repository: UserRepository = UserRepositoryImpl()
) {
    routing {
        post("/register") {
            val params = call.receive<CreateUserParams>()
            val result = repository.registerUser(params)
            call.respond(result.statusCode, result)
        }
    }
}