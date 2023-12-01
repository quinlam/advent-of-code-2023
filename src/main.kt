import org.reflections.Reflections
import utils.Day

fun main() {

    // Set this value to run for a single day, e.g. "Day7"
//    val classMatcher = Regex("Day\\d+")
    val classMatcher = Regex("Day1")

    Reflections()
        .getSubTypesOf(Day::class.java)
        .sortedBy { it.simpleName.replace("\\D".toRegex(), "").toInt() }
        .filter { classMatcher.matches(it.simpleName) }
        .map { it.newInstance() }
        .forEach {
            it.execute()
        }
}
