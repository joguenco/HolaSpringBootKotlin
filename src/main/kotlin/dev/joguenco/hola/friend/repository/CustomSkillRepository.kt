package dev.joguenco.hola.friend.repository

import dev.joguenco.hola.friend.model.Skill

interface CustomSkillRepository {
    fun findAllByFriendId(friendId: Long): List<Skill>
}
