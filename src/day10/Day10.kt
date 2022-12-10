package day10

import utils.Day
import kotlin.text.StringBuilder

/**
 * Actual answers after submitting;
 * part1: 13920
 * part2: EGLHBLFJ
 */
class Day10 : Day<String, List<CycleOperation>>(
    testPart1Result = "13140",
    testPart2Result = "\n" +
        "##..##..##..##..##..##..##..##..##..##..\n" +
        "###...###...###...###...###...###...###.\n" +
        "####....####....####....####....####....\n" +
        "#####.....#####.....#####.....#####.....\n" +
        "######......######......######......####\n" +
        "#######.......#######.......#######.....\n",
) {
    override fun part1Answer(input: List<CycleOperation>): String {
        val cycles = List(6) { index -> 20 + 40 * index }
        var sum = 0

        input.foldIndexed(1) { i, acc, operation ->
            val index = i + 1

            if (cycles.contains(index)) {
                sum += acc * index
            }

            acc + operation.valueChange
        }

        return sum.toString()
    }

    override fun part2Answer(input: List<CycleOperation>): String {
        val cycles = List(6) { index -> 40 + 40 * index }
        val sb = StringBuilder().append("\n")

        input.foldIndexed(1) { i, acc, operation ->
            val index = i + 1

            if (i in IntRange(acc - 1, acc + 1)) {
                sb.append("#")
            } else {
                sb.append(".")
            }

            if (cycles.contains(index)) {
                sb.append("\n")
                acc + 40 + operation.valueChange
            } else {
                acc + operation.valueChange
            }
        }
        return sb.toString()
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
