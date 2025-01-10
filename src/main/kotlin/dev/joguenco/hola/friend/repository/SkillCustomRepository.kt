package dev.joguenco.hola.friend.repository

import dev.joguenco.hola.friend.model.Skill

interface SkillCustomRepository {

    fun findAllByFriendId(friendId: Long): List<Skill>

    fun deleteSkill(id: Long)
}
