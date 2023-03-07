
package com.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// See https://ktor.io/docs/testing.html
fun Application.testing(testing: Boolean = false) {
    routing {
        get("/") {
            call.respondText("Hello World!", ContentType.Text.Plain)
        }
    }
}