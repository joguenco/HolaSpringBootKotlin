package dev.joguenco.hola.friend.mapper

import dev.joguenco.hola.friend.dto.FriendDataDto
import dev.joguenco.hola.friend.dto.FriendResponseDto
import dev.joguenco.hola.friend.dto.FriendDto
import dev.joguenco.hola.friend.model.Friend
import dev.joguenco.hola.shared.dto.RemoveDto

interface FriendMapper {

    fun toEntity(dto: FriendDataDto): Friend
    fun toDtoData(entity: Friend): FriendResponseDto
    fun toDto(entity: Friend): FriendDto
    fun toDtoDelete(entity: Friend): RemoveDto
}