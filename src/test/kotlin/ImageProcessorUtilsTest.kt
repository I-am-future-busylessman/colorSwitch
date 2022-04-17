import ImageProcessorUtilsFixtures.appliedRGB
import ImageProcessorUtilsFixtures.clampedRGB
import org.junit.jupiter.api.Test
import ImageProcessorUtilsFixtures.color
import ImageProcessorUtilsFixtures.compensatedRGB
import ImageProcessorUtilsFixtures.errorMatrixRGB
import ImageProcessorUtilsFixtures.notClampedRGB
import ImageProcessorUtilsFixtures.testLMS
import ImageProcessorUtilsFixtures.testRGB
import kotlin.test.assertEquals

class ImageProcessorUtilsTest {
    val imageProcessorUtils = ImageProcessorUtils()

    @Test
    fun `get RGB from color`() {
        val rgb = imageProcessorUtils.getRGB(color)
        assertEquals(rgb, testRGB)
    }

    @Test
    fun `simulate color blindness`() {
        val lms = imageProcessorUtils.simulateColorBlindness("Protapone", testRGB.toLMS())
        assertEquals(lms, testLMS)
    }

    @Test
    fun `calculate error matrix`() {
        val rgb = imageProcessorUtils.calculateErrorMatrix(testLMS.toRGB(), testRGB)
        assertEquals(rgb, errorMatrixRGB)
    }

    @Test
    fun `apply error modifications`() {
        val rgb = imageProcessorUtils.applyErrorModifications(testRGB)
        assertEquals(rgb, appliedRGB)
    }

    @Test
    fun `add compensation`() {
        val rgb = imageProcessorUtils.addCompensation(appliedRGB, testRGB)
        assertEquals(rgb, compensatedRGB)
    }

    @Test
    fun `clamp RGB`() {
        val rgb = imageProcessorUtils.clampRGB(notClampedRGB)
        assertEquals(rgb, clampedRGB)
    }
}