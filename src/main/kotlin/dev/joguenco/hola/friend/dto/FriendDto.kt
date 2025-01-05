package dev.joguenco.hola.friend.dto

import java.util.Date

data class FriendCreateDto(
    var name: String,
    var birthDate: Date
)

data class FriendCreateResponseDto(
    var id: Long,
    var name: String,
    var birthDate: Date
)

data class FriendDto(
    var name: String,
    var age: Int
)