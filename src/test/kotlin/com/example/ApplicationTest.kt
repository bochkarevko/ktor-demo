package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertEquals
import org.junit.Test

// See https://ktor.io/docs/testing.html
class ApplicationTest {
    @Test
    fun testRoot() {
        testApplication {
            application {
                simple()
            }
            client.get("/").apply {
                assertEquals(HttpStatusCode.OK, status)
                assertEquals("Hello World!", bodyAsText())
            }
        }
    }
}
