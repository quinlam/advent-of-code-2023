package day1

import utils.Day
import java.util.Collections
import java.util.PriorityQueue

/**
 * Actual answers after submitting;
 * part1: 70296
 * part2: 205381
 */
object Day1 : Day<Int, List<Int?>>(
    testPart1Result = 24000,
    testPart2Result = 45000
) {

    override fun part1Answer(input: List<Int?>): Int {
        return getMaxCalories(input, 1)
    }

    override fun part2Answer(input: List<Int?>): Int {
        return getMaxCalories(input, 3)
    }

    private fun getMaxCalories(input: List<Int?>, count: Int): Int {
        var elfLoad = 0
        val priorityQueue = PriorityQueue<Int>(Collections.reverseOrder())

        for (calorie in input) {
            if (calorie == null) {
                priorityQueue.add(elfLoad)
                elfLoad = 0
            } else {
                elfLoad += calorie
            }
        }
        priorityQueue.add(elfLoad)

        var total = 0
        for (i in 1..count) {
            total += priorityQueue.poll()
        }

        return total
    }

    override fun modifyInput(input: List<String>): List<Int?> {
        return input.map { it.toIntOrNull() }
    }
}
