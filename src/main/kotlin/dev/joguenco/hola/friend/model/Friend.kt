package dev.joguenco.hola.friend.model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "friends")
class Friend(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "birth_date")
    var birthDate: Date? = null,

    @OneToMany(mappedBy = "friend", targetEntity = Skill::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var skills: List<Skill> = emptyList()
) {

    constructor(id: Long?, name: String?, birthDate: Date?) : this(id, name, birthDate, emptyList())

    override fun toString(): String {
        return "Friend(id=$id, name=$name)"
    }
}