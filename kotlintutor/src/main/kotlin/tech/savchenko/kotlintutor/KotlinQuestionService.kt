package tech.savchenko.kotlintutor

import org.springframework.stereotype.Service
import java.util.*


@Service
class KotlinQuestionService(val questionRepository: QuestionRepository, val verificationService: VerificationService) {

    fun getAllQuestions(): Iterable<KotlinQuestion> = questionRepository.findAll()


    fun addQuestion(question: KotlinQuestion) {
        val verified = verificationService.verifyQuestion(question)
        if (verified) {
            questionRepository.save(question)
        } else {
            throw InvalidPropertiesFormatException("Question is not valid")
        }
    }

}