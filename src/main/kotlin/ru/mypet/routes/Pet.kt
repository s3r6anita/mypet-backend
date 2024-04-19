package ru.mypet.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.mypet.data.db.CreatePetParams
import ru.mypet.repository.PetRepository
import ru.mypet.repository.PetRepositoryImpl

fun Route.petRoutes(
    repository: PetRepository = PetRepositoryImpl()
) {
    authenticate {
        route("/pet") {
            get {
//                if (customerStorage.isNotEmpty()) {
//                    call.respond(customerStorage)
//                } else {
//                    call.respondText("No customers found", status = HttpStatusCode.OK)
//                }
            }
            get("{email?}") {
                val email = call.parameters["email"]

            }
            post {
                var params = call.receive<CreatePetParams>()

                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim("email").asString()
                params = params.setOwner(email)

                val result = repository.createPet(params)
                call.respond(result.statusCode, result)
            }
            delete("{id?}") {

            }
        }
    }
}