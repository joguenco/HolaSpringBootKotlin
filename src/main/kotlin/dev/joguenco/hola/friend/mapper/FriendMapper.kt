package dev.joguenco.hola.friend.mapper

import dev.joguenco.hola.friend.dto.FriendCreateDto
import dev.joguenco.hola.friend.dto.FriendCreateResponseDto
import dev.joguenco.hola.friend.dto.FriendDto
import dev.joguenco.hola.friend.model.Friend

interface FriendMapper {

    fun toEntity(dto: FriendCreateDto): Friend
    fun toDtoCreate(entity: Friend): FriendCreateResponseDto
    fun toDto(entity: Friend): FriendDto
}