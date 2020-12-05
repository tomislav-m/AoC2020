import java.io.File
import kotlin.math.max

fun main() {
    val lines = File("day5.txt").useLines { it.toList() }
    part1(lines)
    part2(lines)
}

fun part1(lines: List<String>) {
    var max = 0
    for (line in lines) {
        val value = find(line.substring(0, 7), 0, 127) * 8 + find(line.substring(7), 0, 7)
        max = max(value, max)
    }
    println(max)
}

fun part2(lines: List<String>) {
    val list = mutableListOf<Int>()
    for (line in lines) {
        val value = find(line.substring(0, 7), 0, 127) * 8 + find(line.substring(7), 0, 7)
        list.add(value)
    }
    val sortedList = list.sorted()
    var num = sortedList.first()
    for (value in sortedList) {
        if (num != value) {
            println(num)
            return
        }
        ++num
    }
}

fun find(pass: String, lowerArg: Int, upperArg: Int): Int {
    var lower = lowerArg
    var upper = upperArg
    for (character in pass) {
        when (character) {
            'F', 'L' -> upper = (lower + upper) / 2
            'B', 'R' -> lower = (lower + upper) / 2 + 1
        }
    }
    return lower
}