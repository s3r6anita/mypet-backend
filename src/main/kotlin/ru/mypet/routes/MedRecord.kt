package ru.mypet.routes

import com.typesafe.config.ConfigFactory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.mypet.models.medrecordParams.CreateMedRecordParams
import ru.mypet.models.medrecordParams.UpdateMedRecordParams
import ru.mypet.repository.MedRecordRepository
import ru.mypet.repository.MedRecordRepositoryImpl

fun Route.medRecordRoutes(
    repository: MedRecordRepository = MedRecordRepositoryImpl(),
    config: HoconApplicationConfig = HoconApplicationConfig(ConfigFactory.load()),
    claim: String = config.property("ktor.security.jwt.claim").getString()
) {
    authenticate {
        route("/med") {
            get {
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim(claim).asString()
                val result = repository.findAllByOwner(email)
                call.respond(result.statusCode, result)
            }
            get("{id?}") {
                /** излишен (хотя возможно понадобится для экрана профиля) */
                val id = call.parameters["id"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim(claim).asString()
                val result = repository.findById(id, email)
                call.respond(result.statusCode, result)
            }
            post {
                val params = call.receive<CreateMedRecordParams>()
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim(claim).asString()
                val result = repository.createMedRecord(params, email)
                call.respond(result.statusCode, result)
            }
            post("update") {
                val params = call.receive<UpdateMedRecordParams>()
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim(claim).asString()
                val result = repository.updateMedRecord(params, email)
                call.respond(result.statusCode, result)
            }
            delete("{id?}") {
                val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest)
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim(claim).asString()
                val result = repository.deleteMedRecord(id, email)
                call.respond(result.statusCode, result)
            }
        }
    }
}