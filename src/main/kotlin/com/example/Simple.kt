package com.example

import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.simple() {
    routing {
        get("/simple") {
            call.respondText("Hello World!", ContentType.Text.Plain)
        }
    }
}
