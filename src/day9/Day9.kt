package day9

import utils.Day
import kotlin.math.abs

/**
 * Actual answers after submitting;
 * part1: 5619
 * part2: 2376
 */
class Day9 : Day<Int, List<Movement>>(
    testPart1Result = 13,
    testPart2Result = 1,
) {
    override fun part1Answer(input: List<Movement>): Int {
        return trackTail(input, 2)
    }

    override fun part2Answer(input: List<Movement>): Int {
        return trackTail(input, 10)
    }

    private fun trackTail(input: List<Movement>, ropeSize: Int): Int {
        val ropeSegments = List(ropeSize) { Position(0, 0) }
        val tailVisited = mutableSetOf<Position>()

        for (movement in input) {
            ropeSegments[0].move(movement)
            for (i in 1 until ropeSegments.size) {
                val segmentMovement = calculateMovement(ropeSegments[i - 1], ropeSegments[i])
                ropeSegments[i].move(segmentMovement)
            }
            tailVisited.add(ropeSegments.last().copy())
        }

        return tailVisited.size
    }

    private fun calculateMovement(head: Position, tail: Position): Movement {
        if (abs(head.x - tail.x) > 1 || abs(head.y - tail.y) > 1) {
            return Movement((head.x - tail.x).coerceIn(-1, 1), (head.y - tail.y).coerceIn(-1, 1))
        }

        return Movement(0, 0)
    }

    override fun modifyInput(input: List<String>): List<Movement> {
        return input.map {
            val instruction = it.split(" ")
            val direction = instruction[0]
            val distance = instruction[1].toInt()

            when (direction) {
                "L" -> {
                    List(distance) { Movement(-1, 0) }
                }
                "R" -> {
                    List(distance) { Movement(1, 0) }
                }
                "D" -> {
                    List(distance) { Movement(0, -1) }
                }
                "U" -> {
                    List(distance) { Movement(0, 1) }
                }
                else -> {
                    throw RuntimeException("Instruction not understood")
                }
            }
        }.flatten()
    }
}
