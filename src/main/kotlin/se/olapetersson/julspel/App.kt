package se.olapetersson.julspel

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.default
import io.ktor.http.content.defaultResource
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.response.respond
import io.ktor.routing.get
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
    val oneSignalApiKey = System.getenv("OPEN_SIGNAL_API_KEY")
    val isDocker = System.getenv("DOCKER")?.trim()?.toBoolean() ?: false
    val roundTime = System.getenv("ROUND_TIME_MS")?.trim()?.toLong() ?: 3_600_000
    println("STARTING DOCKER WITH $isDocker")
    val mongoUrl = if (isDocker) "mongodb:27017" else "localhost:27017"
    require(oneSignalApiKey != null ) { "YOU NEED TO SET ENV OPEN_SIGNAL_API_KEY" }
    val oneSignalClient = OneSignalClient(oneSignalApiKey)
    val mongoDriver = MongoDriver().setup(mongoUrl)
    val userRepository = UserRepository(mongoDriver)
    val userService = UserService(userRepository)
    val userRoutes = UserRoutes(userService)
    val roundRepository = RoundRepository(mongoDriver)
    val roundService = RoundService(roundRepository, userService, oneSignalClient, roundTime)
    val roundRoutes = RoundRoutes(roundService)
    val questionService = QuestionService(QuestionRepository(mongoDriver))
    val questionRoutes = QuestionRoutes(questionService, roundService)

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    install(CORS) {
        anyHost()
    }

    routing {
        questionRoutes.getRoutes(this)
        roundRoutes.getRoutes(this)
        userRoutes.getRoutes(this)
        static("/") {
            resources("webapp")
            defaultResource("webapp/index.html")
        }
    }

}


