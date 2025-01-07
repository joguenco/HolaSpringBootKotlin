package dev.joguenco.hola.friend.dto

import java.util.Date

data class FriendCreateDto(
    var name: String,
    var birthDate: Date,
    var skills: List<SkillDto> = emptyList(),
)

data class FriendResponseDto(var id: Long, var name: String, var birthDate: Date)

data class FriendDto(
    var name: String,
    var age: Int,
    var skills: MutableList<SkillDto> = mutableListOf(),
)
