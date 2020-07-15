package project.ucsd.micromanager2

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.content.resource
import io.ktor.http.content.static
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080
    embeddedServer(Netty, port = port) {
        routing {
            get("/{...}") {
                call.respondText(
                    this::class.java.classLoader.getResource("index.html")!!.readText(),
                    ContentType.Text.Html
                )
            }
            static("/") {
                resource("index.html")
            }
            static("/static") {
                resource("micromanager2-backend.js")
            }
        }
    }.start(wait = true)
}