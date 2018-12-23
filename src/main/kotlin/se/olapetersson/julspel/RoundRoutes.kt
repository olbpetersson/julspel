package se.olapetersson.julspel

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

class RoundRoutes(private val roundService: RoundService) {

    fun getRoutes(routing: Routing) {
        routing {
            get("round/start") {
                roundService.startRounds()
                call.respond(HttpStatusCode.Accepted, "Started the rounds")

            }
            get("round/whipe") {
                roundService.whipe()
            }
        }
    }
}
