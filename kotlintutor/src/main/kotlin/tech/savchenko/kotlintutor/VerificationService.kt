package tech.savchenko.kotlintutor

import org.springframework.stereotype.Service

@Service
class VerificationService {

    fun verifyQuestion(kotlinQuestion: KotlinQuestion) =
        when (kotlinQuestion.questionType) {
            in 1..5 -> true
            10 -> true
            else -> false
        }
}