package com.example

import com.example.plugins.configureKoin
import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    configureKoin()
    configureMonitoring()
    configureRouting()
}
