import java.awt.Color

object ImageProcessorUtilsFixtures {

    val color = Color(100, 0, 30)

    val testRGB = RGB(
        R = 100.0,
        G = 0.0,
        B = 30.0
    )

    val testLMS = LMS (
        L = 815.2436306764,
        M = 461.5792,
        S = 47.00836
    )

    val errorMatrixRGB = RGB(
        R = 88.76180922348834,
        G = -11.238304801959128,
        B = -0.400576177017264
    )

    val appliedRGB = RGB(
        R = 0.0,
        G = 70.0,
        B = 100.0
    )

    val compensatedRGB = RGB(
        R = 100.0,
        G = 70.0,
        B = 130.0
    )

    val notClampedRGB = RGB(
        R = 260.0,
        G = -1.0,
        B = 10.0
    )

    val clampedRGB = RGB(
        R = 255.0,
        G = 0.0,
        B = 10.0
    )
}