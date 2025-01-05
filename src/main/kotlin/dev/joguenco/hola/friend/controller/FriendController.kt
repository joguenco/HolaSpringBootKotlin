package dev.joguenco.hola.friend.controller

import dev.joguenco.hola.friend.dto.FriendCreateDto
import dev.joguenco.hola.friend.dto.FriendCreateResponseDto
import dev.joguenco.hola.friend.service.FriendService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hola/v1")
class FriendController {

    @Autowired
    lateinit var friendService: FriendService

    @GetMapping("/friends")
    fun getFriends() = friendService.getAllFriends()

    @GetMapping("/friend/{id}")
    fun getFriend(@PathVariable id: Long) : ResponseEntity<Any> {
        val response = friendService.getFriendById(id)

        return if (response != null) {
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/friend")
    fun createFriend(@RequestBody friend: FriendCreateDto) : ResponseEntity<FriendCreateResponseDto> {
        return ResponseEntity.ok(friendService.createFriend(friend))
    }
}
