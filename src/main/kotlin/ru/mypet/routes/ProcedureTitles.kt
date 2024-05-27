package ru.mypet.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.mypet.models.ProcedureTitle
import ru.mypet.models.titlesParams.CreateProcedureTitleParams
import ru.mypet.repository.ProcedureTitleRepository
import ru.mypet.repository.ProcedureTitleRepositoryImpl

fun Route.titleRoutes(
    repository: ProcedureTitleRepository = ProcedureTitleRepositoryImpl(),
) {
    authenticate {
        route("/title") {
            get {
                val result = repository.getAll()
                call.respond(result.statusCode, result)
            }
            post {
                val params = call.receive<CreateProcedureTitleParams>()
                val result = repository.createProcedureTitle(params)
                call.respond(result.statusCode, result)
            }
            post("update") {
                val params = call.receive<ProcedureTitle>()
                val result = repository.updateProcedureTitle(params)
                call.respond(result.statusCode, result)
            }
        }
    }
}
