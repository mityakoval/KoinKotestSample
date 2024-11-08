package com.example.plugins

import com.example.services.ServiceA
import com.example.services.ServiceB
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() = install(Koin) {
    slf4jLogger()

    val config = module {
        single<ServiceA> { ServiceA() }
        single<ServiceB> { ServiceB() }
    }

    modules(config)
}
