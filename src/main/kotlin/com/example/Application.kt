package com.example

import io.ktor.server.application.*
import io.ktor.server.locations.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
@OptIn(KtorExperimentalLocationsAPI::class)
fun Application.module(testing: Boolean = false) {
    simple() // simple text
    html()  // some HTML and CSS
//    freemarker() // Using Freemarker templates
    hocon() // working with custom configuration


    webapp() // Webjars, WebSockets
    features() // different features, mostly headers

    routingApp()
//    structure()

    authentication() // some authentication examples

    locations() // the experimental Locations API

    statusPages() // handling errors

    async() // asynchronous handling

    // FreeMarker has to be installed BEFORE ContentNegotiation
    database()
    json()  // ContentNegotiation. Serializing response to JSON
}