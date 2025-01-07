package dev.joguenco.hola

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class HolaSpringBootKotlinApplication

fun main(args: Array<String>) {
  runApplication<HolaSpringBootKotlinApplication>(*args)
}
