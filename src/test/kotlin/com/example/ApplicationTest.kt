package com.example

import com.example.routing.Response
import com.example.services.ServiceB
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.FunSpec
import io.kotest.koin.KoinExtension
import io.kotest.koin.KoinLifecycleMode
import io.kotest.matchers.shouldBe
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.config.mergeWith
import io.ktor.server.testing.testApplication
import io.mockk.every
import io.mockk.mockkClass
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.mock.declareMock

class ApplicationTest : FunSpec(), KoinTest {


    override fun extensions(): List<Extension> = listOf(
        KoinExtension(
            module = module {},
            mockProvider = {
                mockkClass(it)
            },
            mode = KoinLifecycleMode.Root
        )
    )

    init {
        val appConfig = listOf(
            ApplicationConfig("application.test.conf")
        ).reduce(ApplicationConfig::mergeWith)

        test("Test mock injection") {
            testApplication {
                environment {
                    config = appConfig
                }

                application {
                    val serviceBMock = declareMock<ServiceB>()

                    every { serviceBMock.identify() }.returns("Mock")
                }

                val cli = createClient {
                    install(ContentNegotiation) {
                        json()
                    }
                }

                val resp = cli.get("/test")
                val body = resp.body<Response>()
                body.a shouldBe "ServiceA"
                body.b shouldBe "Mock"

            }
        }
    }
}
