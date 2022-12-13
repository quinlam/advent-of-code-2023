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

        val testInputPart1 = getInput(testFile)
        val testInputPart2 = getInput(testFile)
        val actualInputPart1 = getInput(actualFile)
        val actualInputPart2 = getInput(actualFile)
        tests(testInputPart1, testInputPart2)
        actual(actualInputPart1, actualInputPart2)
    }

    abstract fun part1Answer(input: T): S
    abstract fun part2Answer(input: T): S
    abstract fun modifyInput(input: List<String>): T

    private fun getInput(fileName: String): T {
        val input = File("src", "$fileName.txt")
            .readLines()
        return modifyInput(input)
    }

    private fun tests(input1: T, input2: T) {
        val part1 = part1Answer(input1)
        check(part1 == testPart1Result)
        val part2 = part2Answer(input2)
        check(part2 == testPart2Result)
        println("ALL TESTS VALIDATED")
    }

    private fun actual(input1: T, input2: T) {
        val part1 = part1Answer(input1)
        println("PART 1 ANSWER: $part1")
        val part2 = part2Answer(input2)
        println("PART 2 ANSWER: $part2")
    }
}
