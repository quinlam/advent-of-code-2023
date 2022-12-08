package day2

import utils.Day
import java.lang.RuntimeException

/**
 * Actual answers after submitting;
 * part1: 8890
 * part2: 10238
 */
class Day2 : Day<Int, List<String>>(
    testPart1Result = 15,
    testPart2Result = 12
) {

    override fun part1Answer(input: List<String>): Int {
        return calculateScore(input, matchMap)
    }

    override fun part2Answer(input: List<String>): Int {
        return calculateScore(input, outcomeMap)
    }

    private fun calculateScore(input: List<String>, matchMap: Map<String, ChoiceOutcome>): Int {
        return input.sumOf { evaluateMatch(it, matchMap) }
    }

    private fun evaluateMatch(input: String, matchMap: Map<String, ChoiceOutcome>): Int {
        return matchMap[input]
            ?.score()
            ?: throw RuntimeException("Value not in map")
    }

    override fun modifyInput(input: List<String>): List<String> {
        return input
    }

    private val matchMap = mapOf(
        "A X" to ChoiceOutcome(Choice.ROCK, OutCome.DRAW),
        "A Y" to ChoiceOutcome(Choice.PAPER, OutCome.WIN),
        "A Z" to ChoiceOutcome(Choice.SCISSORS, OutCome.LOSS),
        "B X" to ChoiceOutcome(Choice.ROCK, OutCome.LOSS),
        "B Y" to ChoiceOutcome(Choice.PAPER, OutCome.DRAW),
        "B Z" to ChoiceOutcome(Choice.SCISSORS, OutCome.WIN),
        "C X" to ChoiceOutcome(Choice.ROCK, OutCome.WIN),
        "C Y" to ChoiceOutcome(Choice.PAPER, OutCome.LOSS),
        "C Z" to ChoiceOutcome(Choice.SCISSORS, OutCome.DRAW),
    )

    private val outcomeMap = mapOf(
        "A X" to ChoiceOutcome(Choice.SCISSORS, OutCome.LOSS),
        "A Y" to ChoiceOutcome(Choice.ROCK, OutCome.DRAW),
        "A Z" to ChoiceOutcome(Choice.PAPER, OutCome.WIN),
        "B X" to ChoiceOutcome(Choice.ROCK, OutCome.LOSS),
        "B Y" to ChoiceOutcome(Choice.PAPER, OutCome.DRAW),
        "B Z" to ChoiceOutcome(Choice.SCISSORS, OutCome.WIN),
        "C X" to ChoiceOutcome(Choice.PAPER, OutCome.LOSS),
        "C Y" to ChoiceOutcome(Choice.SCISSORS, OutCome.DRAW),
        "C Z" to ChoiceOutcome(Choice.ROCK, OutCome.WIN),
    )
}
