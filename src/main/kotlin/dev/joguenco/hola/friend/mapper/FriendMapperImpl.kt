package dev.joguenco.hola.friend.mapper

import dev.joguenco.hola.friend.dto.FriendDataDto
import dev.joguenco.hola.friend.dto.FriendResponseDto
import dev.joguenco.hola.friend.dto.FriendDto
import dev.joguenco.hola.friend.model.Friend
import dev.joguenco.hola.shared.dto.RemoveDto
import java.time.LocalDate
import java.time.Period
import java.util.Calendar

class FriendMapperImpl : FriendMapper {
    override fun toEntity(dto: FriendDataDto): Friend {
        return Friend(
            name = dto.name,
            birthDate = dto.birthDate
        )
    }

    override fun toDtoData(entity: Friend): FriendResponseDto {
        return FriendResponseDto(
            id = entity.id!!,
            name = entity.name!!,
            birthDate = entity.birthDate!!
        )
    }

    override fun toDto(entity: Friend): FriendDto {
        val calendar = Calendar.getInstance()
        calendar.time = entity.birthDate
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return FriendDto(
            name = entity.name!!,
            age = Period.between(
                LocalDate.of(year, month, day),
                LocalDate.now()
            ).years
        )
    }

    override fun toDtoDelete(entity: Friend): RemoveDto {
        return RemoveDto(
            deleted = entity.name!!,
        )
    }
}