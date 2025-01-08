package dev.joguenco.hola

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HolaTests {

    @LocalServerPort private var port: Int = 8080

    @BeforeEach
    fun init() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = port
    }

    @Test
    fun `test ping`() {
        RestAssured.given().`when`().get("/hola/v1/ping").then().statusCode(200).log().all()
    }
}
