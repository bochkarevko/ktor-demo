package com.example

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.home() {
    get("/") {
        call.respondText("Index Page")
    }
    get("/about") {
        call.respondText("About Page")
    }
}


fun Route.employees() {
    route("/employee") {
        get {
            call.respondText("Employee Page")
        }
        post {

        }
        delete {

        }
    }
}

fun Application.structure() {
    routing {
        home()
        employees()
    }
}
