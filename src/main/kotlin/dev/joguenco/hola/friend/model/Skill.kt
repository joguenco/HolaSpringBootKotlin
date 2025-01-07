package dev.joguenco.hola.friend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "skills")
class Skill(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(name = "name") var name: String? = null,
    @ManyToOne @JoinColumn(name = "friend_id") @JsonIgnore var friend: Friend? = null
) {
  override fun toString(): String {
    return "Skill(id=$id, name=$name)"
  }
}
