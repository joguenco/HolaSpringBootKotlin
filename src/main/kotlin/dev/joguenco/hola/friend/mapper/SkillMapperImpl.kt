package dev.joguenco.hola.friend.mapper

import dev.joguenco.hola.friend.dto.SkillDto
import dev.joguenco.hola.friend.model.Skill

class SkillMapperImpl : SkillMapper {
    override fun toEntity(dto: SkillDto): Skill {
        return Skill(name = dto.name)
    }

    override fun toDto(entity: Skill): SkillDto {
        return SkillDto(name = entity.name ?: "")
    }
}
