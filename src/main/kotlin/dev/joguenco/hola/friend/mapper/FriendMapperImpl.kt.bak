package dev.joguenco.hola.friend.mapper

import dev.joguenco.hola.friend.dto.FriendDto
import dev.joguenco.hola.friend.dto.FriendInDto
import dev.joguenco.hola.friend.dto.FriendSimpleDto
import dev.joguenco.hola.friend.model.Friend
import dev.joguenco.hola.shared.dto.RemoveDto
import java.time.LocalDate
import java.time.Period
import java.util.Calendar

class FriendMapperImpl(private val skillMapper: SkillMapper) : FriendMapper {

    override fun toEntity(dto: FriendInDto): Friend {
        return Friend(
            name = dto.name,
            birthDate = dto.birthDate,
            skills = dto.skills.map { skillMapper.toEntity(it) }.toMutableList(),
        )
    }

    override fun toSimpleDto(entity: Friend): FriendSimpleDto {
        return FriendSimpleDto(id = entity.id!!, name = entity.name!!)
    }

    override fun toDto(entity: Friend): FriendDto {
        val calendar = Calendar.getInstance()
        calendar.time = entity.birthDate
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return FriendDto(
            id = entity.id!!,
            name = entity.name!!,
            birthDate = entity.birthDate!!,
            age = Period.between(LocalDate.of(year, month, day), LocalDate.now()).years,
        )
    }

    override fun toDtoDelete(entity: Friend): RemoveDto {
        return RemoveDto(deleted = entity.name!!)
    }
}
