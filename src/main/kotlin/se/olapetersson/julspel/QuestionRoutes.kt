package se.olapetersson.julspel

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import org.slf4j.LoggerFactory

class QuestionRoutes(private val questionService: QuestionService, private val roundService: RoundService) {

    private val logger = LoggerFactory.getLogger(QuestionRoutes::javaClass.name)

    fun getRoutes(routing: Routing) {
        routing {
            get("/") {
                call.respond(questionService.getQuestions().map(QuestionMapper::toDto))

            }
            get("/question") {
                val currentRound = roundService.getCurrentRound()!!.currentRoundIndex
                logger.info("Getting question for round $currentRound")
                val question = questionService.getOngoingQuestion(currentRound)
                call.respond(
                    QuestionMapper.toDto(question)
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
