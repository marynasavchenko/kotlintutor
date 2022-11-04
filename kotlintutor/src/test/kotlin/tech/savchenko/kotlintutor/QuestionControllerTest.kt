package tech.savchenko.kotlintutor

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import io.mockk.verify
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@WebMvcTest(QuestionController::class)
class QuestionControllerTest(var mockMvc: MockMvc) : ShouldSpec() {

    override fun extensions() = listOf(SpringExtension)

    @MockkBean(relaxed = true)
    private lateinit var questionsService: KotlinQuestionService

    init {
        should("return all questions") {
            val question1 = kotlinQuestion()
            val question2 = KotlinQuestion(1, "question2", "answer2", 3)
            every { questionsService.getAllQuestions() } returns listOf(question1, question2)
            mockMvc.perform(MockMvcRequestBuilders.get("/kotlinquestions"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].question").value(question1.question))
                .andExpect(jsonPath("\$.[0].answer").value(question1.answer))
                .andExpect(jsonPath("\$.[0].questionType").value(question1.questionType))
        }

        should("post question") {
            val question = kotlinQuestion()
            val objectMapper = ObjectMapper()
            val json = objectMapper.writeValueAsString(question)
            mockMvc.perform(
                MockMvcRequestBuilders.post("/kotlinquestions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
                .andExpect(status().isOk)
            verify { questionsService.addQuestion(question)
            }
        }
    }

    private fun kotlinQuestion() = KotlinQuestion(0, "question1", "answer1", 4)

}
