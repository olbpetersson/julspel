package se.olapetersson.julspel

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post

class QuestionRoutes(private val questionService: QuestionService) {

    fun getRoutes(routing: Routing) {
        routing {
            get("/") {
                call.respond(QuestionMapper.toDto(questionService.getNextQuestion()))

            }
            get("/{id}") {
                val id = call.parameters["id"]
                require( !id.isNullOrBlank() ) { "Id has to be provided" }
                questionService.getNextQuestion()
            }
            post("/correct") {
                // TODO: How to get userId etc
                val answer = Answer("", true, 1)
                questionService.correctQuestion(answer)
            }
        }
    }
}
