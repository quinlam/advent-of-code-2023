package day10

import utils.Day

/**
 * Actual answers after submitting;
 * part1:
 * part2:
 */
class Day10 : Day<Int, List<CycleOperation>>(
    testPart1Result = 13140,
    testPart2Result = 0,
) {
    override fun part1Answer(input: List<CycleOperation>): Int {
        val cycles = List(6) { index -> 20 + 40 * index }
        var sum = 0

        input.foldIndexed(1) { i, acc, operation ->
            val index = i + 1

            if (cycles.contains(index)) {
                sum += acc * index
            }

            acc + operation.valueChange
        }

        return sum
    }

    override fun part2Answer(input: List<CycleOperation>): Int {
        val cycles = List(6) { index -> 40 + 40 * index }
        println("GENERATING SCREEN")

        input.foldIndexed(1) { i, acc, operation ->
            val index = i + 1

            if (i in IntRange(acc - 1, acc + 1)) {
                print("#")
            } else {
                print(".")
            }

            if (cycles.contains(index)) {
                println()
                acc + 40 + operation.valueChange
            } else {
                acc + operation.valueChange
            }
        }
        return 0
    }

    override fun modifyInput(input: List<String>): List<CycleOperation> {
        return input
            .map { createCycleOperations(it) }
            .flatten()
    }

    private fun createCycleOperations(input: String): List<CycleOperation> {
        return if (input == "noop") {
            listOf(CycleOperation(0))
        } else {
            val valueChange = input.split(" ")[1].toInt()
            listOf(CycleOperation(0), CycleOperation(valueChange))
        }
    }
}
