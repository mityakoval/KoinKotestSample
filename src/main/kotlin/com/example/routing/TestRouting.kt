package com.example.routing

import com.example.services.ServiceA
import com.example.services.ServiceB
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Application.testRouting() {
    routing {
        val serviceA by inject<ServiceA>()
        val serviceB by inject<ServiceB>()
        test(serviceA, serviceB)
    }
}

@Serializable
data class Response(val a: String, val b: String)


fun Route.test(serviceA: ServiceA, serviceB: ServiceB) {
    get("/test") {
        call.respond(status = HttpStatusCode.OK, Response(serviceA.identify(), serviceB.identify()))
    }
}

