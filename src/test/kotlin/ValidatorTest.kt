import ValidatorFixtures.invalidType
import ValidatorFixtures.validType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ValidatorTest {
    val validator = Validator()

    @Test
    fun `validate correct type`() {
        assertDoesNotThrow {
            validator.validateType(validType)
        }
    }

    @Test
    fun `validate incorrect type`() {
        val exception = assertThrows<java.lang.RuntimeException> {
            validator.validateType(invalidType)
        }
        assertEquals(exception.message, "Invalid type: $invalidType")
    }

}