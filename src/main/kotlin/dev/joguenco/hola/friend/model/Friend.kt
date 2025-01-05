package dev.joguenco.hola.friend.model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "friend")
class Friend  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "name")
    val name: String? = null

    @Column(name = "birth_date")
    val birthDate: Date? = null
}