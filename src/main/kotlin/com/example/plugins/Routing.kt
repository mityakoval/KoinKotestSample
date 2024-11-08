package com.example.plugins

import com.example.routing.testRouting
import io.ktor.server.application.Application

fun Application.configureRouting() {
    testRouting()
}
