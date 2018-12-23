package se.olapetersson.julspel

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

class UserRoutes(private val userService: UserService) {

    fun getRoutes(routing: Routing) {
        routing {
            get("/user") {
                val createdUser = userService.createUser()
                call.respond(createdUser)
            }
        }
    }
}
