package dev.joguenco.hola.friend.service

import dev.joguenco.hola.friend.dto.*
import dev.joguenco.hola.friend.mapper.FriendMapperImpl
import dev.joguenco.hola.friend.repository.FriendRepository
import dev.joguenco.hola.friend.repository.SkillRepository
import dev.joguenco.hola.shared.dto.RemoveDto
import org.springframework.stereotype.Service

@Service
class FriendService(
    private val friendRepository: FriendRepository,
    private val skillRepository: SkillRepository,
) {

    private val friendMapper = FriendMapperImpl()

    fun createFriend(friendDto: FriendCreateDto): FriendDto? {
        val friend = friendMapper.toEntity(friendDto)
        friend.skills.forEach { it.friend = friend }
        val friendSaved = friendRepository.save(friend)

        return getFriendById(friendSaved.id!!)
    }

    fun getAllFriends(): List<FriendSimpleDto> {
        return friendRepository.findAll().map { friendMapper.toSimpleDto(it) }
    }

    fun getFriendById(id: Long): FriendDto? {
        val friend =
            friendMapper.toDto(
                friendRepository.findById(id).orElse(null) ?: throw Exception("Friend not found")
            )

        val skills = skillRepository.findAllByFriendId(id)
        skills.map { friend.skills.add(SkillDto(it.name.toString())) }

        return friend
    }

    fun updateFriend(id: Long, friendDto: FriendCreateDto): FriendDto? {
        val friend =
            friendRepository.findById(id).orElse(null) ?: throw Exception("Friend not found")

        friend.name = friendDto.name
        friend.birthDate = friendDto.birthDate

        return getFriendById(id)
    }

    fun deleteFriend(id: Long): RemoveDto {
        val friend =
            friendRepository.findById(id).orElse(null) ?: throw Exception("Friend not found")
        friendRepository.deleteById(id)

        return friendMapper.toDtoDelete(friend)
    }
}
