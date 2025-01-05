package dev.joguenco.hola.friend.service

import dev.joguenco.hola.friend.model.Friend
import dev.joguenco.hola.friend.repository.FriendRepository
import org.springframework.stereotype.Service

@Service
class FriendService (private val friendRepository: FriendRepository) {

    fun createFriend(friend: Friend): Friend = friendRepository.save(friend)

    fun getAllFriends(): List<Friend> = friendRepository.findAll()

    fun getFriendById(id: Long): Friend? = friendRepository.findById(id).orElse(null)

    fun updateFriend(friend: Friend): Friend = friendRepository.save(friend)

    fun deleteFriend(id: Long) = friendRepository.deleteById(id)
}