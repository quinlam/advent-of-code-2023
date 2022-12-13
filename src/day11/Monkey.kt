package day11

import java.util.LinkedList

data class Monkey(
    val items: LinkedList<Long>,
    val operation: (Long) -> Long,
    val testNumber: Int,
    val trueMonkey: Int,
    val falseMonkey: Int,
    var inspectionCount: Int = 0
) {

    fun inspect(relief: Int, commonModulo: Int, throwTo: (Int, Long) -> Unit) {
        while (!items.isEmpty()) {
            inspectionCount++
            val worry = (operation(items.pop()) / relief) % commonModulo
            val nextMonkey = if (worry % testNumber == 0L) trueMonkey else falseMonkey
            throwTo(nextMonkey, worry)
        }
    }

    fun giveItem(item: Long) {
        items.add(item)
    }

    companion object {
        fun fromString(input: List<String>): Monkey {
            return Monkey(
                items = createItems(input[1]),
                operation = createOperation(input[2]),
                testNumber = input[3].substringAfterLast(" ").toInt(),
                trueMonkey = input[4].substringAfterLast(" ").toInt(),
                falseMonkey = input[5].substringAfterLast(" ").toInt()
            )
        }

        private fun createItems(input: String): LinkedList<Long> {
            val list = input.filter { it.isDigit() || it == ' ' }
                .trim()
                .split(" ")
                .map { it.toLong() }
                .toList()

            return LinkedList(list)
        }

        private fun createOperation(input: String): (Long) -> Long {
            val parts = input.split(" ")

            return { old ->
                val value = if (parts[7] == "old") old else parts[7].toLong()
                if (parts[6] == "+") old + value
                else old * value
            }
        }
    }
}
