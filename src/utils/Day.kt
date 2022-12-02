package utils

import java.io.File

abstract class Day<S, T>(
    private val testPart1Result: S,
    private val testPart2Result: S
) {
    fun execute() {
        println("--------------------------------------")
        println("Executing ${this.javaClass.simpleName}")

        val className = this.javaClass.simpleName
        val actualFile = "${className.lowercase()}/$className"
        val testFile = "${actualFile}_test"

        val testInput = getInput(testFile)
        val actualInput = getInput(actualFile)
        tests(testInput)
        actual(actualInput)
    }

    abstract fun part1Answer(input: T): S
    abstract fun part2Answer(input: T): S
    abstract fun modifyInput(input: List<String>): T

    private fun getInput(fileName: String): T {
        val input = File("src", "$fileName.txt")
            .readLines()
        return modifyInput(input)
    }

    private fun tests(input: T) {
        val part1 = part1Answer(input)
        check(part1 == testPart1Result)
        val part2 = part2Answer(input)
        check(part2 == testPart2Result)
        println("TESTS VALIDATED")
    }

    private fun actual(input: T) {
        val part1 = part1Answer(input)
        println("PART 1 ANSWER: $part1")
        val part2 = part2Answer(input)
        println("PART 2 ANSWER: $part2")
    }
}
