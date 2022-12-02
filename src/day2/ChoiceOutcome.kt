package day2

import java.lang.RuntimeException

class ChoiceOutcome(private val choice: Choice, private val outcome: OutCome) {
    fun score(): Int {
        val choicePts: Int = choicePoints[choice] ?: throw RuntimeException("value not in choice map")
        val outcomePts: Int = outcomePoints[outcome] ?: throw RuntimeException("value not in outcome map")
        return choicePts + outcomePts
    }

    private val choicePoints = mapOf(
        Choice.ROCK to 1,
        Choice.PAPER to 2,
        Choice.SCISSORS to 3
    )

    private val outcomePoints = mapOf(
        OutCome.WIN to 6,
        OutCome.DRAW to 3,
        OutCome.LOSS to 0,
    )
}
