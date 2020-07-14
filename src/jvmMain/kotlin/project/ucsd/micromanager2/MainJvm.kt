package project.ucsd.micromanager2

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        routing {
            get("/") {
                call.respondHtml {
                    head {
                        title("Hello from Ktor!")
                    }
                    body {
                        +"${hello()} from Ktor."
                        div {
                            id = "js-response"
                            +"Loading..."
                        }
                        script(src = "/static/micromanager2-backend.js") {}
                    }
                }
            }
            static("/static") {
                resource("micromanager2-backend.js")
            }
        }
    }.start(wait = true)
}