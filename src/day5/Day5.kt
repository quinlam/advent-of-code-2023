package day5

import utils.Day
import java.util.Stack

/**
 * Actual answers after submitting;
 * part1: QGTHFZBHV
 * part2: MGDMPSZTM
 */
class Day5 : Day<String, StackInput>(
    testPart1Result = "CMZ",
    testPart2Result = "MCD"
) {
    override fun part1Answer(input: StackInput): String {
        return moveStacks(input, true)
    }

    override fun part2Answer(input: StackInput): String {
        return moveStacks(input)
    }

    private fun moveStacks(input: StackInput, reverse: Boolean = false): String {
        input.instructions.forEach {
            val tempStack = Stack<Char>()
            for (i in 1..it.number) {
                val value = input.stacks[it.stackFrom]?.pop()
                tempStack.push(value)
            }

            if (reverse) {
                tempStack.reverse()
            }

            while (!tempStack.isEmpty()) {
                input.stacks[it.stackTo]?.push(tempStack.pop())
            }
        }

        val stringBuilder = StringBuilder()

        input.stacks.values.forEach {
            stringBuilder.append(it.peek())
        }
        return stringBuilder.toString()
    }

    override fun modifyInput(input: List<String>): StackInput {
        val parts = input
            .map { parseLine(it) }
            .groupBy({ it.type }, { it.line })

        val stackId = parts[ParseLineType.STACK_ID]?.first() ?: ""
        val contents = parts[ParseLineType.STACK_CONTENTS] ?: emptyList()
        val stacks = createStacks(stackId, contents)

        val instructions = parts[ParseLineType.INSTRUCTION]
            ?.map { createInstructions(it) } ?: emptyList()

        return StackInput(stacks, instructions)
    }

    private fun createInstructions(input: String): Instruction {
        val instructionString = input.split(" ")
        return Instruction(
            stackFrom = instructionString[3].toInt(),
            stackTo = instructionString[5].toInt(),
            number = instructionString[1].toInt()
        )
    }

    private fun createStacks(stackIds: String, contents: List<String>): Map<Int, Stack<Char>> {
        val numOfStacks = stackIds.split("\\s+".toRegex())
        val stacks = (1 until numOfStacks.size)
            .associateBy({ it }, { Stack<Char>() })

        contents.forEach {
            it.toCharArray().forEachIndexed { index, element ->
                if (element.isLetter()) {
                    val stack = (index / 4) + 1
                    stacks[stack]?.push(element)
                }
            }
        }

        stacks.values.forEach { it.reverse() }

        return stacks
    }

    private fun parseLine(input: String): IdentifiedLine {
        return when {
            input.startsWith(" 1") -> {
                IdentifiedLine(ParseLineType.STACK_ID, input)
            }
            input.isBlank() -> {
                IdentifiedLine(ParseLineType.BLANK, input)
            }
            input.startsWith("move") -> {
                IdentifiedLine(ParseLineType.INSTRUCTION, input)
            }
            else -> IdentifiedLine(ParseLineType.STACK_CONTENTS, input)
        }
    }
}
