package tech.savchenko.kotlintutor

import io.kotest.core.spec.style.FreeSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KotlinTutorApplicationTest : FreeSpec() {

    override fun extensions() = listOf(SpringExtension)

    init {
        "Should load context" {
        }
    }
}


