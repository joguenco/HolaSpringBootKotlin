package dev.joguenco.hola.friend.repository

import dev.joguenco.hola.friend.model.Skill
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository

@Transactional
@Repository
class SkillRepository : SkillCustomRepository {

    @PersistenceContext lateinit var entityManager: EntityManager

    override fun findAllByFriendId(friendId: Long): List<Skill> {
        return entityManager
            .createQuery("select s from Skill s where s.friend.id = :friendId", Skill::class.java)
            .setParameter("friendId", friendId)
            .resultList
    }

    override fun deleteSkill(id: Long) {
        val skill = findById(id) ?: throw Exception("Skill not found")
        entityManager.remove(skill)
    }

    fun findById(id: Long): Skill? {
        return entityManager.find(Skill::class.java, id)
    }
}
