package tech.savchenko.kotlintutor

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kotlinquestions")
class QuestionController(val service: KotlinQuestionService) {

    @GetMapping
    fun getQuestions() = ResponseEntity.ok(service.getAllQuestions())

    @PostMapping
    fun addQuestion(@RequestBody kotlinQuestion: KotlinQuestion): ResponseEntity<String> {
        service.addQuestion(kotlinQuestion)
        return ResponseEntity.ok("Question is created")
    }
}