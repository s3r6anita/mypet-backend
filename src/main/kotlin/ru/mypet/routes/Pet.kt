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
import ru.mypet.models.petParams.CreatePetParams
import ru.mypet.models.petParams.UpdatePetParams
import ru.mypet.repository.PetRepository
import ru.mypet.repository.PetRepositoryImpl

fun Route.petRoutes(
    repository: PetRepository = PetRepositoryImpl(),
    config: HoconApplicationConfig = HoconApplicationConfig(ConfigFactory.load()),
    claim: String = config.property("ktor.security.jwt.claim").getString()
) {
    authenticate {
        route("/pet") {
            get {
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim(claim).asString()
                val result = repository.findAllByOwner(email)
                call.respond(result.statusCode, result)
            }
            get("{id?}") { /** излишен (хотя возможно понадобится для экрана профиля) */
                val id = call.parameters["id"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim(claim).asString()
                val result = repository.findById(id, email)
                call.respond(result.statusCode, result)
            }
            post {
                val params = call.receive<CreatePetParams>()
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim(claim).asString()
                val result = repository.createPet(params, email)
                call.respond(result.statusCode, result)
            }
            post("update") {
                val params = call.receive<UpdatePetParams>()
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim(claim).asString()
                val result = repository.updatePet(params, email)
                call.respond(result.statusCode, result)
            }
            delete("{id?}") {
                val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest)
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim(claim).asString()
                val result = repository.deletePet(id, email)
                call.respond(result.statusCode, result)
            }
        }
    }
}