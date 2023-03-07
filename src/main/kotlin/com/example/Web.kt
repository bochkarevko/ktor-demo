package com.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.webjars.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.time.Duration


// See https://ktor.io/docs/webjars.html
// See https://ktor.io/docs/websocket.html
// See https://play.kotlinlang.org/hands-on/Creating%20a%20WebSocket%20Chat%20with%20Ktor/01_introduction
fun Application.webapp(){

    //region Webjars
    install(Webjars) {
        path = "/webjars" //defaults to /webjars
    }

    routing {
        get("/webjars") {
            call.respondText("<script src='/webjars/jquery/jquery.js'></script>", ContentType.Text.Html)
        }
    }
    //endregion

    //region WebSockets
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        webSocket("/") { // websocketSession
            for (frame in incoming) {
                when (frame) {
                    is Frame.Text -> {
                        val text = frame.readText()
                        outgoing.send(Frame.Text("YOU SAID: $text"))
                        if (text.equals("bye", ignoreCase = true)) {
                            close(CloseReason(CloseReason.Codes.NORMAL, "Client said BYE"))
                        }
                    }
                    is Frame.Binary -> TODO()
                    is Frame.Close -> TODO()
                    is Frame.Ping -> TODO()
                    is Frame.Pong -> TODO()
                }
            }
        }
    }
    //endregion

}