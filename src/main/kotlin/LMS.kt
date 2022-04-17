data class LMS(
    val L: Double,
    val M: Double,
    val S: Double
)

fun LMS.toRGB() =
    RGB(
        R = (0.0809444479 * L) + (-0.130504409 * M) + (0.116721066 * S),
        G = (-0.0102485335 * L) + (0.0540193266 * M) + (-0.113614708 * S),
        B = (-0.000365296938 * L) + (-0.00412161469 * M) + (0.693511405 * S)
    )
