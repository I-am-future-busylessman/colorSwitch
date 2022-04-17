class Validator {
    private val types = listOf("Protapone", "Deuteranope", "Tritanope")
    fun validateType(type: String) {
        if (!(type in types))
            throw java.lang.RuntimeException("Invalid type: $type")
    }
}