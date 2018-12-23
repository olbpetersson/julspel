package se.olapetersson.julspel

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post

class QuestionRoutes(private val questionService: QuestionService, private val roundService: RoundService) {

    fun getRoutes(routing: Routing) {
        routing {
            get("/") {
                call.respond(questionService.getQuestions().map(QuestionMapper::toDto))

            }
            get("/question") {
                call.respond(
                    QuestionMapper.toDto(
                        questionService.getOngoingQuestion(roundService.getCurrentRound()!!.currentRoundIndex)
                    )
                )
            }
            post("question/validate") {
                val answer = call.receive<Answer>()
                val validatedAnswer = questionService.validateAnswer(answer)
                if (validatedAnswer) {
                    roundService.givePointsToUser(answer.userId)
                }
                call.respond(HttpStatusCode.Accepted, "Got it!")
            }
        }
    }
}
