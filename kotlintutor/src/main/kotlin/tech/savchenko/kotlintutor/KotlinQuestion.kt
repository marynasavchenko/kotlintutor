package tech.savchenko.kotlintutor

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class KotlinQuestion(
    @Id @GeneratedValue val id: Long,
    val question: String,
    val answer: String,
    val questionType: Int
)
