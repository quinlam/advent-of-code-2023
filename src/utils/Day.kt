package utils

abstract class Day<S, T>(
    private val testPart1Result: S,
    private val testPart2Result: S
) {
    fun execute() {
        println("Executing Day One")
        val testInput = getTestInput()
        val actualInput = getActualInput()
        tests(testInput)
        actual(actualInput)
    }

    abstract fun part1Answer(input: T): S
    abstract fun part2Answer(input: T): S

    abstract fun getTestInput(): T
    abstract fun getActualInput(): T

    private fun tests(input: T) {
        val part1 = part1Answer(input)
        check(part1 == testPart1Result)
        val part2 = part2Answer(input)
        check(part2 == testPart2Result)
        println("TESTS VALIDATED")
    }

    private fun actual(input: T) {
        val part1 = part1Answer(input)
        check(part1 == 70296)
        println("PART 1 ANSWER: $part1")
        val part2 = part2Answer(input)
        check(part2 == 205381)
        println("PART 2 ANSWER: $part2")
    }
}
