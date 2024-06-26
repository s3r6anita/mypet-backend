package ru.mypet.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.mypet.models.userParams.CreateUserParams
import ru.mypet.models.userParams.LoginUserParams
import ru.mypet.repository.UserRepository
import ru.mypet.repository.UserRepositoryImpl

fun Route.authRoutes(
    repository: UserRepository = UserRepositoryImpl()
) {
    post("/loginVK") {
        val params = call.receive<CreateUserParams>()
        val result = repository.loginUserByVK(params)
        call.respond(result.statusCode, result)
    }
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
//    authenticate {
//        get("/refreshToken") {
//            val params = call.receive<TokenPair>()
//            val result = repository.refreshTokenUser(params)
//            call.respond(result.statusCode, result)
//        }
//    }
}