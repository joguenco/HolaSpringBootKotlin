package dev.joguenco.hola.friend.service

import dev.joguenco.hola.friend.dto.FriendCreateDto
import dev.joguenco.hola.friend.dto.FriendCreateResponseDto
import dev.joguenco.hola.friend.dto.FriendDto
import dev.joguenco.hola.friend.mapper.FriendMapperImpl
import dev.joguenco.hola.friend.model.Friend
import dev.joguenco.hola.friend.repository.FriendRepository
import org.springframework.stereotype.Service

@Service
class FriendService (private val friendRepository: FriendRepository) {

    private val friendMapper = FriendMapperImpl()

    fun createFriend(friendDto: FriendCreateDto): FriendCreateResponseDto {

        val friendSaved = friendRepository.save(friendMapper.toEntity(friendDto))

        println("Friend saved in database: $friendSaved")
        return friendMapper.toDtoCreate(friendSaved)
    }

    fun getAllFriends(): List<Friend> = friendRepository.findAll()

    fun getFriendById(id: Long): FriendDto? {
        println("Friend found in database: ${friendRepository.findById(id).orElse(null)}")
        return friendMapper.toDto(friendRepository.findById(id).orElse(null))
    }

    fun updateFriend(friend: Friend): Friend = friendRepository.save(friend)

    fun deleteFriend(id: Long) = friendRepository.deleteById(id)
}