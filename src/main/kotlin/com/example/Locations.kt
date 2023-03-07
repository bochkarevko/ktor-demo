package com.example

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.locations.*
import io.ktor.server.plugins.dataconversion.DataConversion
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.converters.*
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.br
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@KtorExperimentalLocationsAPI
@Location("/index")
class Index(val message: String = "Hello from index!")

@KtorExperimentalLocationsAPI

//@Location("/employee/{id}") //  employee/Anton?project=Kotlin
@Location("/employee/{id}/{project}") //  employee/Anton/Kotlin
class Employee(
    val id: String, val project: String, val dob: LocalDate
)

@KtorExperimentalLocationsAPI
@Location("/employee")
class EmployeeList

// See https://ktor.io/docs/features-locations.html
@KtorExperimentalLocationsAPI
fun Application.locations() {
    install(Locations)

    install(DataConversion) {
        convert<LocalDate> { // this: DelegatingConversionService
            val formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd")

            decode { values -> // converter: (values: List<String>, type: Type) -> Any?
                values.singleOrNull()?.let { LocalDate.parse(it) }
                    ?: throw DataConversionException("Cannot convert $values as Date")
            }

            encode { value -> // converter: (value: Any) -> List<String>
                listOf(formatter.format(value))
            }
        }
    }

    routing {
        get<Index> {
            call.respondText("Locations demo: ${it.message}")
        }
        get<Employee> {
            call.respondText("Employee: ${it.id}. Project: ${it.project}. DoB: ${it.dob}")
        }
        get<EmployeeList> {
            val employees = getEmployeesFromDB()

            call.respondHtml {
                body {
                    employees.forEach {
                        a(locations.href(it)) { +it.id }
                        br
                    }
                }
            }
        }
    }
}

@KtorExperimentalLocationsAPI
fun getEmployeesFromDB(): List<Employee> = listOf(
    Employee("Anton", "Kotlin", LocalDate.now()),
    Employee("Hadi", "Wasabi", LocalDate.now()),
    Employee("Leonid", "Ktor", LocalDate.now()),
)
