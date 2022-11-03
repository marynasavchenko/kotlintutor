package tech.savchenko.kotlintutor

import org.springframework.data.repository.CrudRepository

interface QuestionRepository : CrudRepository<KotlinQuestion, Long>