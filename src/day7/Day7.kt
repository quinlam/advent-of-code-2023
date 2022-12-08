package day7

import utils.Day
import kotlin.RuntimeException

/**
 * Actual answers after submitting;
 * part1: 2031851
 * part2: 2568781
 */
class Day7 : Day<Int, List<Directory>>(
    testPart1Result = 95437,
    testPart2Result = 24933642
) {
    override fun part1Answer(input: List<Directory>): Int {
        return input
            .filter { it.getSize() <= 100000 }
            .sumOf { it.getSize() }
    }

    override fun part2Answer(input: List<Directory>): Int {
        val rootSize = input.find { it.name == "/" }
            ?.getSize() ?: throw RuntimeException("Can not find root directory")
        val freeSpaceNeeded = 30000000 - (70000000 - rootSize)

        return input
            .filter { it.getSize() > freeSpaceNeeded }
            .minBy { it.getSize() }
            .getSize()
    }

    override fun modifyInput(input: List<String>): List<Directory> {
        var currentDirectory: Directory = NullDirectory()
        val directoryMap = mutableListOf<Directory>()
        input.forEach {
            val action = determineAction(it)
            when {
                (action == CommandAction.ENTER_DIRECTORY) -> {
                    val name = it.split(" ")[2]
                    currentDirectory = createNewDirectory(name, currentDirectory)
                    directoryMap.add(currentDirectory)
                }
                (action == CommandAction.EXIT_DIRECTORY) -> {
                    currentDirectory = currentDirectory.exit()
                }
                (action == CommandAction.FILE_LISTING) -> {
                    val fileSize = it.split(" ")[0].toInt()
                    currentDirectory.addFile(fileSize)
                }
                else -> {}
            }
        }
        return directoryMap
    }

    private fun createNewDirectory(
        name: String,
        currentDirectory: Directory
    ): Directory {
        if (name == "/") {
            return RootDirectory(name)
        }

        val childName = "${currentDirectory.name}$name/"
        return ChildDirectory(childName, currentDirectory)
    }

    private fun determineAction(input: String): CommandAction {
        return when {
            input.startsWith("$ cd ..") -> {
                CommandAction.EXIT_DIRECTORY
            }
            input.startsWith("$ cd") -> {
                CommandAction.ENTER_DIRECTORY
            }
            input.startsWith("dir") -> {
                CommandAction.DIRECTORY_LISTING
            }
            input.startsWith("$ ls") -> {
                CommandAction.LIST
            }
            input[0].isDigit() -> {
                CommandAction.FILE_LISTING
            }
            else -> throw RuntimeException("Command not understood")
        }
    }
}
