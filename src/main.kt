import org.reflections.Reflections
import utils.Day

fun main() {

    // Set this value to run for a single day, e.g. "Day7"
    val runOnlyTest: String? = null

    Reflections()
        .getSubTypesOf(Day::class.java)
        .sortedBy { it.simpleName }
        .filter { runOnlyTest == null || it.simpleName == runOnlyTest }
        .map { it.newInstance() }
        .forEach {
            it.execute()
        }
}
