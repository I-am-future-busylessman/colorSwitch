data class RGB(
    val R: Double,
    val G: Double,
    val B: Double
)
fun RGB.toLMS() =
    LMS(
        L = (17.8824 * R) + (43.5161 * G) + (4.11935 * B),
        M = (3.45565 * R) + (27.1554 * G) + (3.86714 * B),
        S = (0.0299566 * R) + (0.184309 * G) + (1.46709 * B)
    )
