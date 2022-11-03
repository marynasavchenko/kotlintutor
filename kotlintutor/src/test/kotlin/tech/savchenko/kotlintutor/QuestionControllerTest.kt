package tech.savchenko.kotlintutor

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import io.mockk.verify
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@WebMvcTest(QuestionController::class)
class QuestionControllerTest(var mockMvc: MockMvc) : ShouldSpec() {

    override fun extensions() = listOf(SpringExtension)

    @MockkBean(relaxed = true)
    private lateinit var questionsService: KotlinQuestionService

    init {
        should("return all questions") {
            every { questionsService.getAllQuestions() } returns emptyList()
            mockMvc.perform(MockMvcRequestBuilders.get("/kotlinquestions"))
                .andExpect(MockMvcResultMatchers.content().json("[]"))
                .andExpect(MockMvcResultMatchers.status().isOk)
        }

        should("post question") {
            mockMvc.perform(
                MockMvcRequestBuilders.post("/kotlinquestions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{ \"question\": \"" + "question1" + "\", " +
                                "\"answer\":\"" + "answer1" + "\"," +
                                "\"questionType\":\"" + "4" + "\"}"
                    )
            )
                .andExpect(MockMvcResultMatchers.status().isOk)
            verify { questionsService.addQuestion(kotlinQuestion()) }
        }
    }

    private fun kotlinQuestion() = KotlinQuestion(0, "question1", "answer1", 4)

}
