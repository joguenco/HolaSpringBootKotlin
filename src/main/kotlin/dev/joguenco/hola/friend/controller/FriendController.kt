package dev.joguenco.hola.friend.controller

import dev.joguenco.hola.friend.dto.FriendCreateDto
import dev.joguenco.hola.friend.dto.FriendDto
import dev.joguenco.hola.friend.service.FriendService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hola/v1")
class FriendController {

    @Autowired lateinit var friendService: FriendService

    @GetMapping("/friends") fun getFriends() = friendService.getAllFriends()

    @GetMapping("/friends/{id}")
    fun getFriend(@PathVariable id: Long): ResponseEntity<Any> {
        try {
            val friend = friendService.getFriendById(id)
            return ResponseEntity.ok(friend)
        } catch (e: Exception) {
            return ResponseEntity.noContent().build()
        }
    }

    @PostMapping("/friends")
    fun createFriend(@RequestBody friend: FriendCreateDto): ResponseEntity<FriendDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(friendService.createFriend(friend))
    }

    @PutMapping("/friends/{id}")
    fun updateFriend(
        @PathVariable id: Long,
        @RequestBody friendDto: FriendCreateDto,
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(friendService.updateFriend(id, friendDto))
        } catch (e: Exception) {
            ResponseEntity.noContent().build()
        }
    }

    @DeleteMapping("/friends/{id}")
    fun deleteFriend(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val friend = friendService.deleteFriend(id)
            return ResponseEntity.ok(friend)
        } catch (e: Exception) {
            ResponseEntity.noContent().build()
        }
    }
}
