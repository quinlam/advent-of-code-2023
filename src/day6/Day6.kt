package day6

import utils.Day
import java.lang.RuntimeException

/**
 * Actual answers after submitting;
 * part1: 1892
 * part2: 2313
 */
class Day6 : Day<Int, CharArray>(
    testPart1Result = 11,
    testPart2Result = 26
) {
    override fun part1Answer(input: CharArray): Int {
        return findUniqueStringForSize(input, 4)
    }

    override fun part2Answer(input: CharArray): Int {
        return findUniqueStringForSize(input, 14)
    }

    private fun findUniqueStringForSize(input: CharArray, size: Int): Int {
        val setQueue = SetQueue<Char>(size)
        input.forEachIndexed { index, element ->
            if (setQueue.isFull()) {
                return index
            }
            setQueue.add(element)
        }
        throw RuntimeException("No start sequence found")
    }

    override fun modifyInput(input: List<String>): CharArray {
        return input.first().toCharArray()
    }
}
