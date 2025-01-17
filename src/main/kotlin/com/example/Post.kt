package com.example

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Application.post() {
    routing {
        get("/") {
            val contentType = ContentType.Text.Html.withCharset(Charsets.UTF_8)

            call.respondHtml {
                head {
                    title { +"POST" }
                    meta {
                        httpEquiv = HttpHeaders.ContentType
                        content = contentType.toString()
                    }
                }
                body {
                    p {
                        +"File upload example"
                    }
                    form(action = "form", encType = FormEncType.multipartFormData, method = FormMethod.post) {
                        acceptCharset = "utf-8"
                        textInput { name = "field1" }
                        br
                        fileInput { name = "file1" }
                        br
                        submitInput { value = "send" }
                    }
                }
            }
        }

        post("/form") {
            val multipart = call.receiveMultipart()
            call.respondTextWriter {
                if (!call.request.isMultipart()) {
                    appendLine("Not a multipart request")
                } else {
                    multipart.forEachPart { part ->
                        when (part) {
                            is PartData.FormItem -> appendLine("Form field: $part = ${part.value}")
                            is PartData.FileItem -> appendLine("File field: $part -> ${part.originalFileName} of ${part.contentType}")
                            else -> TODO()
                        }
                        part.dispose()
                    }
                }
            }

        }
    }
}