import java.io.File

fun main() {
    val input: List<String> = File("day8.txt").useLines { it.toList() }
    part1(input)
    part2(input, setOf())
}

fun part1(input: List<String>) {
    val visited = mutableSetOf<Int>()
    var index = 0
    var acc = 0
    while (true) {
        if (visited.contains(index)) {
            println(acc)
            break
        } else {
            visited.add(index)
        }

        val split = input[index].split(" ")
        when (split[0]) {
            "nop" -> ++index
            "acc" -> {
                acc += split[1].toInt()
                ++index
            }
            "jmp" -> index += split[1].toInt()
        }
    }
}

fun part2(input: List<String>, visited: Set<Int>, changed: Boolean = false, index: Int = 0, acc: Int = 0): Boolean {
    val visited = visited.toMutableSet()
    var index = index
    var acc = acc
    while (index < input.size) {
        if (visited.contains(index)) {
            return false
        } else {
            visited.add(index)
        }

        val split = input[index].split(" ")
        when(split[0]) {
            "nop" -> {
                if (!changed) {
                    val newIndex = index + split[1].toInt()
                    val newInput = input.toMutableList()
                    newInput[index] = input[index].replace("nop", "jmp")
                    val end = part2(input, visited, true, newIndex, acc)
                    if (end) {
                        return true
                    }
                }
                ++index
            }
            "jmp" -> {
                if (!changed) {
                    val newInput = input.toMutableList()
                    newInput[index] = input[index].replace("jmp", "nop")
                    val end = part2(newInput, visited, true, index + 1, acc)
                    if (end) {
                        return true
                    }
                }
                index += split[1].toInt()
            }
            else -> {
                ++index
                acc += split[1].toInt()
            }
        }
    }
    println(acc)
    return true
}