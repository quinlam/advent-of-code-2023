package day11

import utils.Day

/**
 * Actual answers after submitting;
 * part1: 111210
 * part2: 15447387620
 */
class Day11 : Day<Long, Map<Int, Monkey>>(
    testPart1Result = 10605,
    testPart2Result = 2713310158,
) {
    override fun part1Answer(input: Map<Int, Monkey>): Long {
        val relief = 3
        val numberOfRounds = 20

        return calculateMonkeyBusiness(relief, numberOfRounds, input)
    }

    override fun part2Answer(input: Map<Int, Monkey>): Long {
        val relief = 1
        val numberOfRounds = 10000

        return calculateMonkeyBusiness(relief, numberOfRounds, input)
    }

    private fun calculateMonkeyBusiness(worryLevel: Int, rounds: Int, monkeys: Map<Int, Monkey>): Long {

        val commonModulo = monkeys.values.fold(1) { acc, m ->
            acc * m.testNumber
        }

        for (i in 1..rounds) {
            monkeys.values.forEach {
                it.inspect(worryLevel, commonModulo) {
                    index, item ->
                    monkeys[index]?.giveItem(item)
                }
            }
        }

        return monkeys.values
            .map { it.inspectionCount.toLong() }
            .sorted()
            .takeLast(2)
            .reduce { acc, i -> acc * i }
    }

    override fun modifyInput(input: List<String>): Map<Int, Monkey> {
        return input
            .withIndex()
            .groupBy { it.index / 7 }
            .mapValues {
                it.value.map { indexedValue -> indexedValue.value }
            }
            .mapValues { Monkey.fromString(it.value) }
    }
}
