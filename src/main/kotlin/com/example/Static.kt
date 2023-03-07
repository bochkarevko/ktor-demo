package com.example

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

//See https://ktor.io/docs/serving-static-content.html
fun Application.static() {
    routing {
        static("/static") {
            resources("static")
        }
    }
}

