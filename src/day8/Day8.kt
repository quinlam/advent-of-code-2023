package day8

import utils.ArrayMovement
import utils.Day

/**
 * Actual answers after submitting;
 * part1: 1179
 * part2: 172224
 */
class Day8 : Day<Int, Array<Array<Int>>>(
    testPart1Result = 21,
    testPart2Result = 8,
) {

    /**
     * Part1 can be done easily in O(n) but then i don't get to reuse any code...
     */
    override fun part1Answer(input: Array<Array<Int>>): Int {
        return createVisibilityArray(input, true, this::isVisibleFromEdge)
            .flatten()
            .count { it != 0 }
    }

    override fun part2Answer(input: Array<Array<Int>>): Int {
        return createVisibilityArray(input, false, this::visibilityScore)
            .flatten()
            .max()
    }

    private fun createVisibilityArray(
        input: Array<Array<Int>>,
        findEdge: Boolean,
        function: (List<Int>) -> Int
    ): Array<Array<Int>> {
        val visible = Array(input.size) { Array(input[0].size) { 0 } }

        for (row in input.indices) {
            for (column in input[row].indices) {
                val treeSize = input[row][column]
                val values = ArrayMovement.values()
                    .map { move(input, treeSize, row, column, it, findEdge) }

                visible[row][column] = function(values)
            }
        }

        return visible
    }

    private fun move(input: Array<Array<Int>>, treeSize: Int, row: Int, column: Int, movement: ArrayMovement, findEdge: Boolean): Int {
        val i = row + movement.horizontal
        val j = column + movement.vertical
        if (input.getOrNull(i)?.getOrNull(j) == null) {
            return findEdge.compareTo(false)
        }

        if (treeSize > input[i][j]) {
            return move(input, treeSize, i, j, movement, findEdge) + findEdge.compareTo(true)
        }

        return findEdge.compareTo(true)
    }

    private fun isVisibleFromEdge(values: List<Int>): Int {
        return values.max()
    }

    private fun visibilityScore(values: List<Int>): Int {
        return values.reduce { acc, i -> acc * i}
    }

    override fun modifyInput(input: List<String>): Array<Array<Int>> {
        return input.map {
            it.toCharArray()
                .map { char -> char.digitToInt() }
                .toTypedArray()
        }.toTypedArray()
    }
}
