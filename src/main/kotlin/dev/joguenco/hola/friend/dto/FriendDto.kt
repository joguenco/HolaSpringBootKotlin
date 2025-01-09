package dev.joguenco.hola.friend.dto

import java.util.Date

/** Data Transfer Object for creating and update a Friend. */
data class FriendCreateDto(
    var name: String,
    var birthDate: Date,
    var skills: List<SkillDto> = emptyList(),
)

data class FriendSimpleDto(var id: Long, var name: String)

data class FriendDto(
    var id: Long,
    var name: String,
    var birthDate: Date,
    var age: Int,
    var skills: MutableList<SkillDto> = mutableListOf(),
)
