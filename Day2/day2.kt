import java.io.File

fun main() {
    val lines = File("day2.txt").useLines { it.toList()}
    part1(lines)
    part2(lines)
}

fun part1(lines: List<String>) {
    var count = 0
    for (line in lines) {
        val split = line.split(" ")
        val pair = Pair(split[0].substringBefore("-"), split[0].substringAfter("-"))
        val character = split[1][0]
        val occurrences = split[2].count { ch -> ch == character }
        if (occurrences < pair.first.toInt() || occurrences > pair.second.toInt()) {
            continue
        }
        ++count
    }
    println(count)
}

fun part2(lines: List<String>) {
    var count = 0
    for (line in lines) {
        val split = line.split(" ")
        val pair = Pair(split[0].substringBefore("-"), split[0].substringAfter("-"))
        val character = split[1][0]
        if ((split[2][pair.first.toInt() - 1] == character) xor (split[2][pair.second.toInt() - 1] == character)) {
            ++count
        }
    }
    println(count)
}