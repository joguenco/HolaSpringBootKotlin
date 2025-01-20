package dev.joguenco.hola.friend.mapper

import dev.joguenco.hola.friend.dto.FriendDto
import dev.joguenco.hola.friend.dto.FriendInDto
import dev.joguenco.hola.friend.dto.FriendSimpleDto
import dev.joguenco.hola.friend.dto.SkillDto
import dev.joguenco.hola.friend.model.Friend
import dev.joguenco.hola.friend.model.Skill
import dev.joguenco.hola.shared.dto.RemoveDto
import java.time.LocalDate
import java.time.Period
import java.util.*
import java.util.Calendar
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(componentModel = "spring")
interface FriendMapper {

    @Mapping(target = "skills", source = "skills") fun toEntity(dto: FriendInDto): Friend

    fun mapSkills(sourceSkills: List<SkillDto>): List<Skill> {
        var skills: List<Skill> = emptyList()

        sourceSkills.forEach { it.name.let { name -> skills += Skill(name = name) } }

        return skills
    }

    fun toSimpleDto(entity: Friend): FriendSimpleDto

    @Mapping(target = "age", source = "birthDate", qualifiedByName = ["calculateAge"])
    fun toDto(entity: Friend): FriendDto

    @Named("calculateAge")
    fun calculateAge(birthDate: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = birthDate
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return Period.between(LocalDate.of(year, month, day), LocalDate.now()).years
    }

    @Mapping(target = "deleted", source = "name") fun toDtoDelete(entity: Friend): RemoveDto
}
