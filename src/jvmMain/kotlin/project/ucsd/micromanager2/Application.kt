package project.ucsd.micromanager2

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.content.resource
import io.ktor.http.content.static
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import project.ucsd.micromanager2.graphql.installGraphQL

fun Application.mmModule() {
    installGraphQL()
    install(ContentNegotiation) {
        gson()
    }

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
}