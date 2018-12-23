package se.olapetersson.julspel

import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.auth.basic.BasicAuth
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.response.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent

class OneSignalClient(val API_KEY: String) {
    val url = "https://onesignal.com/api/v1/notifications"

    private val client = HttpClient(Apache) {
        install(BasicAuth) {
            username = API_KEY
            password = API_KEY
        }
    }

    suspend fun sendNotification() {
        val payload = Gson().toJson(OneSignalNotification())
        println("SENDING BODY $payload")
        client.post<HttpResponse>(url) {
            body = TextContent(payload, ContentType.Application.Json)
            header("Authorization", "BASIC $API_KEY")
        }
        client.post<HttpResponse>()
    }

    data class OneSignalNotification(
        val app_id: String = "f4656014-e875-48c9-8d75-7752f1705931",
        val contents: Map<String, String> = mapOf("en" to "New question available!"),
        val included_segments: List<String> = listOf("Subscribed Users"))
}
