package dev.joguenco.hola.friend.repository

import dev.joguenco.hola.friend.model.Friend
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FriendRepository : JpaRepository<Friend, Long> {

    @Query(
        "select new dev.joguenco.hola.friend.model.Friend(f.id, f.name, f.birthDate) from Friend f"
    )
    override fun findAll(): MutableList<Friend>
}
