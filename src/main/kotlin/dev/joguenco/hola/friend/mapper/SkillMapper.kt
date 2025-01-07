package dev.joguenco.hola.friend.mapper

import dev.joguenco.hola.friend.dto.SkillDto
import dev.joguenco.hola.friend.model.Skill

interface SkillMapper {
    fun toEntity(dto: SkillDto): Skill

    fun toDto(entity: Skill): SkillDto
}
