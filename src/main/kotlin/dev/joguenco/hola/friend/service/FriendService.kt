package dev.joguenco.hola.friend.service

import dev.joguenco.hola.friend.dto.FriendDataDto
import dev.joguenco.hola.friend.dto.FriendResponseDto
import dev.joguenco.hola.friend.dto.FriendDto
import dev.joguenco.hola.friend.mapper.FriendMapperImpl
import dev.joguenco.hola.friend.model.Friend
import dev.joguenco.hola.friend.repository.FriendRepository
import dev.joguenco.hola.shared.dto.RemoveDto
import org.springframework.stereotype.Service

@Service
class FriendService (private val friendRepository: FriendRepository) {

    private val friendMapper = FriendMapperImpl()

    fun createFriend(friendDto: FriendDataDto): FriendResponseDto {

        val friendSaved = friendRepository.save(friendMapper.toEntity(friendDto))

        return friendMapper.toDtoData(friendSaved)
    }

    fun getAllFriends(): List<Friend> = friendRepository.findAll()

    fun getFriendById(id: Long): FriendDto? {
        return friendMapper.toDto(friendRepository.findById(id).orElse(null) ?: throw Exception("Friend not found"))
    }

    fun updateFriend(id: Long, friendDto: FriendDataDto): FriendResponseDto {
        val friend = friendRepository.findById(id).orElse(null) ?: throw Exception("Friend not found")

        friend.name = friendDto.name
        friend.birthDate = friendDto.birthDate

        return friendMapper.toDtoData(friendRepository.save(friend))
    }

    fun deleteFriend(id: Long): RemoveDto {
        val friend = friendRepository.findById(id).orElse(null) ?: throw Exception("Friend not found")
        friendRepository.deleteById(id)

        return friendMapper.toDtoDelete(friend)
    }
}