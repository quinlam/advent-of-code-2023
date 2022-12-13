package day12

import utils.ArrayMovement
import utils.Day

/**
 * Actual answers after submitting;
 * part1: 420
 * part2: 414
 */
class Day12 : Day<Int, Array<Array<Char>>>(
    testPart1Result = 31,
    testPart2Result = 29,
) {
    override fun part1Answer(input: Array<Array<Char>>): Int {
        val endNode = findE(input)
        return bfs(endNode, 'S', input)
    }

    override fun part2Answer(input: Array<Array<Char>>): Int {
        val endNode = findE(input)
        return bfs(endNode, 'a', input)
    }

    private fun bfs(fromNode: BFSNode, startNode: Char, input: Array<Array<Char>>): Int {
        val visited = HashSet<Pair<Int, Int>>()
        val queue = ArrayDeque<BFSNode>()
        queue.add(fromNode)
        visited.add(fromNode.location)

        while (!queue.isEmpty()) {
            val currentNode = queue.removeFirst()

            if (currentNode.letter == startNode) {
                return currentNode.steps
            }

            val newNodes = getNodes(currentNode, input, visited)
            queue.addAll(newNodes)
        }

        return Integer.MAX_VALUE
    }

    private fun findE(input: Array<Array<Char>>): BFSNode {
        input
            .forEachIndexed { i, arr ->
                arr.forEachIndexed { j, it ->
                    if (it == 'E') {
                        return BFSNode(Pair(i, j), 0, it)
                    }
                }
            }

        throw RuntimeException("Can't find the end")
    }

    private fun getNodes(node: BFSNode, input: Array<Array<Char>>, visited: HashSet<Pair<Int, Int>>): List<BFSNode> {
        val row = node.location.first
        val col = node.location.second

        return ArrayMovement.values()
            .map { Pair(row + it.horizontal, col + it.vertical) }
            .filter {
                input.getOrNull(it.first)?.getOrNull(it.second) != null
            }
            .filter { canStep(node.letter, input[it.first][it.second]) }
            .filter { !visited.contains(it) }
            .map { BFSNode(it, node.steps + 1, input[it.first][it.second]) }
            .onEach { visited.add(it.location) }
    }

    private fun canStep(current: Char, next: Char): Boolean {
        return current.elevation() - next.elevation() <= 1
    }

    private fun Char.elevation(): Int {
        return when (this) {
            'S' -> 'a'.code
            'E' -> 'z'.code
            else -> this.code
        }
    }

    override fun modifyInput(input: List<String>): Array<Array<Char>> {
        return input.map { it.toCharArray().toTypedArray() }
            .toTypedArray()
    }
}
