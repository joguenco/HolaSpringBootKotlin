package dev.joguenco.hola.friend.model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "friends")
class Friend  (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name")
    val name: String? = null,

    @Column(name = "birth_date")
    val birthDate: Date? = null
) {
    override fun toString(): String {
        return "Friend(id=$id, name=$name)"
    }
}