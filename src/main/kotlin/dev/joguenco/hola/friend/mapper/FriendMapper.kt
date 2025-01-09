package dev.joguenco.hola.friend.mapper

import dev.joguenco.hola.friend.dto.FriendCreateDto
import dev.joguenco.hola.friend.dto.FriendDto
import dev.joguenco.hola.friend.dto.FriendSimpleDto
import dev.joguenco.hola.friend.model.Friend
import dev.joguenco.hola.shared.dto.RemoveDto

interface FriendMapper {

    fun toEntity(dto: FriendCreateDto): Friend

    fun toSimpleDto(entity: Friend): FriendSimpleDto

    fun toDto(entity: Friend): FriendDto

    fun toDtoDelete(entity: Friend): RemoveDto
}
