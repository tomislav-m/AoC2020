import java.io.File

fun main() {
    val input: MutableList<Int> = File("day10.txt").useLines { it.map { line -> line.toInt() }.sorted().toMutableList() }
    input.add(0, 0)
    part1(input)
    part2(input)
}

fun part1(input: List<Int>) {
    var ones = 0
    var threes = 0
    for (jolts in input) {
        if (input.contains(jolts + 1)) {
            ++ones
        } else if (input.contains(jolts + 3)) {
            ++threes
        }
    }
    println(ones * ++threes)
}

fun part2(input: List<Int>) {
    val countMap = mutableMapOf<Int, Long>()

    for (jolts in input) {
        for (i in 1 until 4) {
            val key = jolts + i
            if (input.contains(key)) {
                countMap[key] = countMap.getOrDefault(key, 0) + countMap.getOrDefault(jolts, 1)
            }
        }
    }
    println(countMap[input.last()])
}