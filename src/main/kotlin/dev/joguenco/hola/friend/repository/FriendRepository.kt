package dev.joguenco.hola.friend.repository

import dev.joguenco.hola.friend.model.Friend
import org.springframework.data.jpa.repository.JpaRepository

interface FriendRepository : JpaRepository<Friend, Long>