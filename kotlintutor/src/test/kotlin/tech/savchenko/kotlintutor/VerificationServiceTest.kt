package tech.savchenko.kotlintutor

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe


class VerificationServiceTest : ShouldSpec() {


    init {
        should("return true when type in a range 1 to 5") {
            val verResult = VerificationService().verifyQuestion(createQuestion(4))

            verResult shouldBe true
        }

        should("return false when 0") {
            val verResult = VerificationService().verifyQuestion(createQuestion(0))

            verResult shouldBe false
        }
    }

    private fun createQuestion(type: Int): KotlinQuestion {
        return KotlinQuestion(1, "Question1", "Answer1", type)
    }

}
