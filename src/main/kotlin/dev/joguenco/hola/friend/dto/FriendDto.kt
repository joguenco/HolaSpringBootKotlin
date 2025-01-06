package dev.joguenco.hola.friend.dto

import java.util.Date

data class FriendDataDto(
    var name: String,
    var birthDate: Date
)

data class FriendResponseDto(
    var id: Long,
    var name: String,
    var birthDate: Date
)

data class FriendDto(
    var name: String,
    var age: Int
)

data class FriendDeleteDto(
    var name: String
)