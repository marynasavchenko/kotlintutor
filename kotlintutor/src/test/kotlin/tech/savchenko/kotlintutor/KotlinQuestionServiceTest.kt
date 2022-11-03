package tech.savchenko.kotlintutor

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify

class KotlinQuestionServiceTest : ShouldSpec() {

    @MockK(relaxed = true)
    private lateinit var questionRepository: QuestionRepository

    @MockK(relaxed = true)
    private lateinit var verificationService: VerificationService

    @InjectMockKs
    lateinit var kotlinQuestionService: KotlinQuestionService

    override fun beforeTest(testCase: TestCase) {
        MockKAnnotations.init(this)
    }

    override fun afterTest(testCase: TestCase, result: TestResult) {
        clearAllMocks()
    }

    init {
        should("save question when verified") {
            val kotlinQuestion = createQuestion()
            every { verificationService.verifyQuestion(kotlinQuestion) } returns true
            every { questionRepository.save(kotlinQuestion) } returns kotlinQuestion
            kotlinQuestionService.addQuestion(kotlinQuestion)
            verify { questionRepository.save(kotlinQuestion) }
        }

        should("throw an exception when not verified") {
            val kotlinQuestion = createQuestion()
            every { verificationService.verifyQuestion(kotlinQuestion) } returns false
            verify(exactly = 0) { questionRepository.save(kotlinQuestion) }
        }
    }

    private fun createQuestion(): KotlinQuestion {
        return KotlinQuestion(1, "Question1", "Answer1", 2)
    }
}
