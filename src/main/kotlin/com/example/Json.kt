package com.example

import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
// import io.ktor.serialization.gson.*

@Serializable
data class Customer(val id: Int, val name: String, val email: String)

@Serializable
data class Response(val count: Int, val data: List<String?>)

// See https://ktor.io/docs/serialization.html
@OptIn(ExperimentalSerializationApi::class)
fun Application.json() {
    install(ContentNegotiation) {
//        gson {
//            setPrettyPrinting()
//            serializeNulls()
//        }

        json(Json {
            prettyPrint = true
            explicitNulls = true
        })

//        jackson {
//            enable(SerializationFeature.INDENT_OUTPUT)
//        }

    }
    routing {
        get("/customer") {
            val model = Customer(id = 1, name = "Anton Arhipov", email = "anton@arhipov.xyz")
            call.respond(model)
        }

        get("/list") {
            val l = listOf("a", "list", "of", "strings", null)
            val model = Response(l.size, l)
            call.respond(model)
        }
    }
}