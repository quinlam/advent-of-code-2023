package utils

enum class ArrayMovement(
    val horizontal: Int,
    val vertical: Int
) {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0)
}
