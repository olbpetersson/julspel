package se.olapetersson.julspel

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    val serverPort = 8080
    println("Server started and is listening on $serverPort")
    embeddedServer(Netty, watchPaths = listOf("julspel"), port = 8080, module = Application::mainModule).start(wait = true)


}

fun Application.mainModule() {

    // Dependency context
    val mongoDriver = MongoDriver().setup()
    val userRepository = UserRepository(mongoDriver)
    val userService = UserService(userRepository)
    val userRoutes = UserRoutes(userService)
    val roundRepository = RoundRepository(mongoDriver)
    val roundService = RoundService(roundRepository, userService)
    val roundRoutes = RoundRoutes(roundService)
    val questionService = QuestionService(QuestionRepository(mongoDriver))
    val questionRoutes = QuestionRoutes(questionService, roundService)

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    routing {
        questionRoutes.getRoutes(this)
        roundRoutes.getRoutes(this)
        userRoutes.getRoutes(this)
    }

}


