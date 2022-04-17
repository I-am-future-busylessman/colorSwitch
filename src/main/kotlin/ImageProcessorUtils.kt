import java.awt.Color

class ImageProcessorUtils {

    private val cvdMatrix = mapOf(
        "Protapone" to listOf(
            0.0, 2.02344, -2.52581,
            0.0, 1.0,      0.0,
            0.0, 0.0,      1.0),
        "Deuteranope" to listOf(
            1.0, 0.0, 0.0,
            0.494207, 0.0, 1.24827,
            0.0,      0.0, 1.0),
        "Tritanope" to listOf(
            1.0,       0.0,      0.0,
            0.0,       1.0,      0.0,
            -0.395913, 0.801109, 0.0)
    )

    fun getRGB(color: Color) =
        RGB(
            R = color.red.toDouble(),
            G = color.green.toDouble(),
            B = color.blue.toDouble()
        )

    fun simulateColorBlindness(type: String, lms: LMS): LMS {
        val cvd = cvdMatrix[type]!!
        return LMS(
            L = (cvd[0] * lms.L) + (cvd[1] * lms.M) + (cvd[2] * lms.S),
            M = (cvd[3] * lms.L) + (cvd[4] * lms.M) + (cvd[5] * lms.S),
            S = (cvd[6] * lms.L) + (cvd[7] * lms.M) + (cvd[8] * lms.S),
        )
    }

    fun calculateErrorMatrix(simulatedRGB: RGB, defaultRGB: RGB) =
        RGB(
            R = defaultRGB.R - simulatedRGB.R,
            G = defaultRGB.G - simulatedRGB.G,
            B = defaultRGB.B - simulatedRGB.B

        )

    fun applyErrorModifications(isolatedRGB: RGB) =
        RGB(
            R = (0.0 * isolatedRGB.R) + (0.0 * isolatedRGB.G) + (0.0 * isolatedRGB.B),
            G = (0.7 * isolatedRGB.R) + (1.0 * isolatedRGB.G) + (0.0 * isolatedRGB.B),
            B = (0.7 * isolatedRGB.R) + (0.0 * isolatedRGB.G) + (1.0 * isolatedRGB.B),
        )

    fun addCompensation(appliedRGB: RGB, defaultRGB: RGB) =
        RGB (
            R = appliedRGB.R + defaultRGB.R,
            G = appliedRGB.G + defaultRGB.G,
            B = appliedRGB.B + defaultRGB.B
        )

    fun clampRGB(rgb: RGB) =
        RGB(
            R = clampColor(rgb.R),
            G = clampColor(rgb.G),
            B = clampColor(rgb.B)
        )

    private fun clampColor(color: Double): Double{
        var colorCopy = color

        if(colorCopy < 0) {
            colorCopy = 0.0
        }
        if(colorCopy > 255){
            colorCopy = 255.0}

        return colorCopy
    }
}