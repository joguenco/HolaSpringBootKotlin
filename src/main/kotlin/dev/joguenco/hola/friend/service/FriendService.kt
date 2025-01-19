package dev.joguenco.hola.friend.service

import dev.joguenco.hola.friend.dto.*
import dev.joguenco.hola.friend.mapper.FriendMapper
import dev.joguenco.hola.friend.model.Skill
import dev.joguenco.hola.friend.repository.FriendRepository
import dev.joguenco.hola.friend.repository.SkillRepository
import dev.joguenco.hola.shared.dto.RemoveDto
import org.springframework.stereotype.Service

@Service
class FriendService(
    private val friendRepository: FriendRepository,
    private val skillRepository: SkillRepository,
    private val friendMapper: FriendMapper,
) {

    fun createFriend(friendDto: FriendInDto): FriendDto? {
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
        skills.map { friend.skills.add(SkillDto(it.id, it.name.toString())) }

        return friend
    }

    fun updateFriend(id: Long, friendDto: FriendInDto): FriendDto? {
        val friend =
            friendRepository.findById(id).orElse(null) ?: throw Exception("Friend not found")

        friend.name = friendDto.name
        friend.birthDate = friendDto.birthDate

        friend.skills.forEach {
            friendDto.skills.forEach { skillDto ->
                if (it.id == skillDto.id) {
                    it.name = skillDto.name
                }
            }
        }

        friendDto.skills.map { skillDto ->
            if (skillDto.id == null) {
                Skill(friend = friend, name = skillDto.name).let { skill ->
                    skillRepository.save(skill)
                }
            }
        }

        return getFriendById(id)
    }

    fun deleteFriend(id: Long): RemoveDto {
        val friend =
            friendRepository.findById(id).orElse(null) ?: throw Exception("Friend not found")
        friendRepository.deleteById(id)

        return friendMapper.toDtoDelete(friend)
    }

    fun deleteSkill(id: Long, skillId: Long): RemoveDto {
        friendRepository.findById(id).orElse(null) ?: throw Exception("Friend not found")
        skillRepository.deleteSkill(skillId)

        return RemoveDto("Skill removed")
    }
}
