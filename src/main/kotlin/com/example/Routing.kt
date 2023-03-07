package com.example

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.routingApp() {
    routing {
        route("routes") {
            get {
                call.respondText("Routing Demo")
            }
            route("customer") {
                get {
                    call.respondText(call.request.queryParameters["id"].toString())
                }
                post {
                    call.respondText("Performed a POST")
                }
            }
            route("employee") {
                get("{id}") {
                    call.respondText(call.parameters["id"].toString())
                }
            }
            route("shipment") {
                route("international") {
                    get {
                        call.respondText("International Shipments")
                    }
                }
                route("national") {
                    get {
                        call.respondText("National Shipments")
                    }
                }
            }
        }
    }
}