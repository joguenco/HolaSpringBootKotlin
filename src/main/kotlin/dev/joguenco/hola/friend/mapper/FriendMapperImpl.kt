package dev.joguenco.hola.friend.mapper

import dev.joguenco.hola.friend.dto.FriendCreateDto
import dev.joguenco.hola.friend.dto.FriendCreateResponseDto
import dev.joguenco.hola.friend.dto.FriendDto
import dev.joguenco.hola.friend.model.Friend
import java.time.LocalDate
import java.time.Period
import java.util.Calendar

class FriendMapperImpl : FriendMapper {
    override fun toEntity(dto: FriendCreateDto): Friend {
        return Friend(
            name = dto.name,
            birthDate = dto.birthDate
        )
    }

    override fun toDtoCreate(entity: Friend): FriendCreateResponseDto {
        return FriendCreateResponseDto(
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


        println("Date ${entity.birthDate}")
        println("LocalDate.of($year, $month, $day)")

        return FriendDto(
            name = entity.name!!,
            age = Period.between(
                LocalDate.of(year, month, day),
                LocalDate.now()
            ).years
        )
    }
}