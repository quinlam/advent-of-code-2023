package day9

data class Position(
    var x: Int,
    var y: Int
) {
    fun move(movement: Movement) {
        x += movement.horizontal
        y += movement.vertical
    }

    fun plus(movement: Movement): Position {
        return Position(x + movement.horizontal, y + movement.vertical)
    }
}
