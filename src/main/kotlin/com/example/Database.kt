package com.example

import com.example.kweet.kweet
import io.ktor.server.application.*

// See the full demo at
// https://github.com/ktorio/ktor-samples/tree/main/kweet
fun Application.database() {
    kweet()
}