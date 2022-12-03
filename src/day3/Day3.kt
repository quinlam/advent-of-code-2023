package day3

import utils.Day

/**
 * Actual answers after submitting;
 * part1: 7845
 * part2: 2790
 */
object Day3 : Day<Int, List<String>>(
    testPart1Result = 157,
    testPart2Result = 70
) {
    override fun part1Answer(input: List<String>): Int {
        return input
            .map { it.chunked(it.length / 2) }
            .map { findIntersection(it) }
            .sumOf { scoreChar(it) }
    }

    override fun part2Answer(input: List<String>): Int {
        return input.chunked(3)
            .map { findIntersection(it) }
            .sumOf { scoreChar(it) }
    }

    private fun findIntersection(input: List<String>): Char {
        return input
            .map { it.toSet() }
            .reduce { acc, next -> acc.intersect(next) }
            .first()
    }

    private fun scoreChar(input: Char): Int {
        val code = input.code
        if (code > 96) {
            return code - 96
        }
        return code - 38
    }

    override fun modifyInput(input: List<String>): List<String> {
        return input
    }
}
