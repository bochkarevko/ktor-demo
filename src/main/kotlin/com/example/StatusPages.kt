package com.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

//See https://ktor.io/docs/status-pages.html
fun Application.statusPages() {
    install(StatusPages) {
        exception<AuthenticationException> { call, cause ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> { call, cause ->
            call.respond(HttpStatusCode.Forbidden)
        }
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError)
        }
    }

    routing {
        get("exception-auth") {
            throw AuthorizationException()
        }
        get("exception-any") {
            throw RuntimeException("Just an exception")
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
