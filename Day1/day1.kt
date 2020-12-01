import java.io.File

fun main() {
    val lines = File("day1.txt").useLines { it.map { line -> line.toInt() }.toList() }
    part1(lines)
    part2(lines)
}

fun part1(lines: List<Int>) {
    val year = 2020
    val numSet = lines.toHashSet()
    for (num in lines) {
        if (numSet.contains(year - num)) {
            println((year - num) * num)
            return
        }
    }
}

fun part2(lines: List<Int>) {
    val year = 2020
    val numSet = lines.toHashSet()
    for (i in lines.indices) {
        for (j in i+1 until lines.size-1 step 1) {
            if (numSet.contains(year - lines[i] - lines[j])) {
                println(lines[i] * lines[j] * (year - lines[i] - lines[j]))
                return
            }
        }
    }
}