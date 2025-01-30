package dev.joguenco.hola

import dev.joguenco.hola.friend.model.Friend
import dev.joguenco.hola.friend.repository.FriendRepository
import java.text.SimpleDateFormat
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatabaseSeeder(private val friendRepository: FriendRepository) : CommandLineRunner {

    val logger = LoggerFactory.getLogger(DatabaseSeeder::class.java)

    override fun run(vararg args: String) {
        logger.info("Adding friends to the database")
        friendRepository.count().let {
            if (it == 0L) {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                friendRepository.saveAll(
                    listOf(
                        Friend(name = "John Doe", birthDate = dateFormat.parse("1990-01-01")),
                        Friend(name = "Jane Doe", birthDate = dateFormat.parse("1991-01-01")),
                        Friend(name = "Alice Doe", birthDate = dateFormat.parse("1992-01-01")),
                    )
                )
            }
        }
    }
}
