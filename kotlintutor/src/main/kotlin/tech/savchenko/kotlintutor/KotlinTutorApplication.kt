package tech.savchenko.kotlintutor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinTutorApplication

fun main(args: Array<String>) {
    runApplication<KotlinTutorApplication>(*args)
}
