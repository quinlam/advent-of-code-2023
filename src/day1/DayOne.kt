package day1

import readInputAsInt
import utils.Day
import java.util.Collections
import java.util.PriorityQueue

object DayOne : Day<Int, List<Int?>>(
    testPart1Result = 24000,
    testPart2Result = 45000
) {

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

    override fun getTestInput(): List<Int?> {
        return readInputAsInt("day1/Day01_test")
    }

    override fun getActualInput(): List<Int?> {
        return readInputAsInt("day1/Day01")
    }

    override fun part1Answer(input: List<Int?>): Int {
        return getMaxCalories(input, 1)
    }

    override fun part2Answer(input: List<Int?>): Int {
        return getMaxCalories(input, 3)
    }
}
