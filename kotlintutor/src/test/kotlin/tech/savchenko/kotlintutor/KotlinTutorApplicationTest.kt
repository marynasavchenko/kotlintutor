package tech.savchenko.kotlintutor

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KotlinTutorApplicationTest : ShouldSpec() {
    override fun extensions() = listOf(SpringExtension)

    init {
        should("Load context") {
        }
    }
}


