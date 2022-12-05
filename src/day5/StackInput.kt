package day5

import java.util.Stack

data class StackInput(
    val stacks: Map<Int, Stack<Char>>,
    val instructions: List<Instruction>
)
