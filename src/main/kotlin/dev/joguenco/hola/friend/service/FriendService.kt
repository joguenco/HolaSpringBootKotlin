package dev.joguenco.hola.friend.service

import dev.joguenco.hola.friend.dto.FriendCreateDto
import dev.joguenco.hola.friend.dto.FriendResponseDto
import dev.joguenco.hola.friend.dto.FriendDto
import dev.joguenco.hola.friend.mapper.FriendMapperImpl
import dev.joguenco.hola.friend.model.Friend
import dev.joguenco.hola.friend.repository.FriendRepository
import dev.joguenco.hola.shared.dto.RemoveDto
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Service

@Service
class FriendService (private val friendRepository: FriendRepository) {

    private val friendMapper = FriendMapperImpl()

    fun createFriend(friendDto: FriendCreateDto): FriendResponseDto {
        val friend = friendMapper.toEntity(friendDto)
        friend.skills.forEach { it.friend = friend }
        val friendSaved = friendRepository.save(friend)

        return friendMapper.toDtoData(friendSaved)
    }

//    @Query("SELECT new dev.joguenco.hola.friend.dto.FriendDto(f.name, YEAR(CURRENT_DATE) - YEAR(f.birthDate)) FROM Friend f")
    fun getAllFriends(): List<Friend> = friendRepository.findAll()

    fun getFriendById(id: Long): FriendDto? {
        return friendMapper.toDto(friendRepository.findById(id).orElse(null) ?: throw Exception("Friend not found"))
    }

    fun updateFriend(id: Long, friendDto: FriendCreateDto): FriendResponseDto {
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