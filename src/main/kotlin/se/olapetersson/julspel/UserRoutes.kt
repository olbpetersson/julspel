package se.olapetersson.julspel

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post

class UserRoutes(private val userService: UserService) {

    fun getRoutes(routing: Routing) {
        routing {
            get("/user") {
                call.respond(userService.getUsers())
            }
            post("/user") {
                val createdUser = userService.createUser()
                call.respond(createdUser)
            }
        }
    }
}

