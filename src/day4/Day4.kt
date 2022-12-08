package day4

import utils.Day

/**
 * Actual answers after submitting;
 * part1: 644
 * part2: 926
 */
class Day4 : Day<Int, List<CampRanges>>(
    testPart1Result = 2,
    testPart2Result = 4
) {
    override fun part1Answer(input: List<CampRanges>): Int {
        return input.sumOf { rangeIsContained(it) }
    }

    override fun part2Answer(input: List<CampRanges>): Int {
        return input.sumOf { rangeOverlaps(it) }
    }

    private fun rangeIsContained(range: CampRanges): Int {
        if (range.range1Start >= range.range2Start &&
            range.range1End <= range.range2End
        ) {
            return 1
        }

        if (range.range2Start >= range.range1Start &&
            range.range2End <= range.range1End
        ) {
            return 1
        }

        return 0
    }

    private fun rangeOverlaps(range: CampRanges): Int {
        if (range.range1Start >= range.range2Start && range.range1Start <= range.range2End) {
            return 1
        }

        if (range.range2Start >= range.range1Start && range.range2Start <= range.range1End) {
            return 1
        }

        return 0
    }

    override fun modifyInput(input: List<String>): List<CampRanges> {
        return input
            .map { it.split(",") }
            .map { createCampRanges(it) }
    }

    private fun createCampRanges(input: List<String>): CampRanges {
        val range1 = input[0].split("-")
        val range2 = input[1].split("-")

        return CampRanges(
            range1Start = range1[0].toInt(),
            range1End = range1[1].toInt(),
            range2Start = range2[0].toInt(),
            range2End = range2[1].toInt(),
        )
    }
}
