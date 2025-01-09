package dev.joguenco.hola

import io.restassured.RestAssured
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus

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
        RestAssured.given()
            .`when`()
            .get("/hola/v1/ping")
            .then()
            .statusCode(HttpStatus.OK.value())
            .log()
            .all()
    }

    @Test
    fun `test create a new friend and delete it`() {
        val newFriend = mapOf("name" to "Test Friend", "birthDate" to "1900-01-04")

        val savedFriend =
            RestAssured.given()
                .contentType("application/json")
                .body(newFriend)
                .`when`()
                .post("/hola/v1/friends")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .log()
                .all()

        val idFriendCreated = savedFriend.extract().path<Int>("id")
        val nameFriendCreated = savedFriend.extract().path<String>("name")

        val deletedFriend =
            RestAssured.given()
                .`when`()
                .delete("/hola/v1/friends/$idFriendCreated")
                .then()
                .statusCode(HttpStatus.OK.value())
                .log()
                .all()

        val nameDeletedFriend = deletedFriend.extract().path<String>("deleted")

        assertEquals(nameFriendCreated, nameDeletedFriend)
    }
}
