package dev.joguenco.hola

import io.restassured.RestAssured
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue
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
        val ageFriendCreated = savedFriend.extract().path<Int>("age")

        assertTrue { ageFriendCreated > 0 }

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

    @Test
    fun `test create two new friends and deleting then`() {
        val friends =
            listOf(
                mapOf("name" to "Friend One", "birthDate" to "1990-01-01"),
                mapOf("name" to "Friend Two", "birthDate" to "1992-02-02"),
            )

        val createdFriends =
            friends.map { friend ->
                RestAssured.given()
                    .contentType("application/json")
                    .body(friend)
                    .`when`()
                    .post("/hola/v1/friends")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .log()
                    .all()
                    .extract()
            }

        val friendsList =
            RestAssured.given()
                .`when`()
                .get("/hola/v1/friends")
                .then()
                .statusCode(HttpStatus.OK.value())
                .log()
                .all()

        val friendsCount = friendsList.extract().jsonPath().getList<Any>("").size
        assertTrue { friendsCount > 1 }

        val ids = createdFriends.map { it.path<Int>("id") }

        ids.forEach { id ->
            RestAssured.given()
                .`when`()
                .delete("/hola/v1/friends/$id")
                .then()
                .statusCode(HttpStatus.OK.value())
                .log()
                .all()
        }
    }

    @Test
    fun `test create a new friend, update it and delete it`() {
        val newFriend = mapOf("name" to "Test Friend", "birthDate" to "1900-01-04")
        val renameFriend = mapOf("name" to "My Friend", "birthDate" to "1900-01-04")

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

        val updatedFriend =
            RestAssured.given()
                .contentType("application/json")
                .body(renameFriend)
                .`when`()
                .put("/hola/v1/friends/$idFriendCreated")
                .then()
                .statusCode(HttpStatus.OK.value())
                .log()
                .all()

        val idFriendUpdated = updatedFriend.extract().path<Int>("id")
        val nameFriendUpdated = updatedFriend.extract().path<String>("name")

        assertEquals(idFriendCreated, idFriendUpdated)
        assertNotEquals(nameFriendCreated, nameFriendUpdated)

        RestAssured.given()
            .`when`()
            .delete("/hola/v1/friends/$idFriendCreated")
            .then()
            .statusCode(HttpStatus.OK.value())
            .log()
            .all()
    }

    @Test
    fun `test create a new friend, fetch friend and delete it`() {
        val myNewFriend =
            mapOf(
                "name" to "My Friend",
                "birthDate" to "1900-01-04",
                "skills" to
                    listOf(
                        mapOf("name" to "QA"),
                        mapOf("name" to "Engineer"),
                        mapOf("name" to "Developer"),
                    ),
            )

        val savedFriend =
            RestAssured.given()
                .contentType("application/json")
                .body(myNewFriend)
                .`when`()
                .post("/hola/v1/friends")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .log()
                .all()

        val idFriendCreated = savedFriend.extract().path<Int>("id")

        val myFriend =
            RestAssured.given()
                .contentType("application/json")
                .`when`()
                .get("/hola/v1/friends/$idFriendCreated")
                .then()
                .statusCode(HttpStatus.OK.value())
                .log()
                .all()
                .extract()

        val skillsOfMyFriend = myFriend.jsonPath().getList<Any>("skills")
        assertEquals(skillsOfMyFriend.size, 3)

        RestAssured.given()
            .`when`()
            .delete("/hola/v1/friends/$idFriendCreated")
            .then()
            .statusCode(HttpStatus.OK.value())
            .log()
            .all()
    }
}
