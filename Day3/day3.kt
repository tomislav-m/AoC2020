import java.io.File
import java.math.BigInteger

fun main() {
    val lines = File("day3.txt").useLines { it.toList()}
    part1(lines)
    part2(lines)
}

fun part1(lines: List<String>) {
    var elem = Pair(3, 1)
    var count = countTrees(elem, lines)
    println(count)
}

fun part2(lines: List<String>) {
    val arr = arrayOf(Pair(1, 1), Pair (3, 1), Pair(5, 1), Pair (7, 1), Pair (1, 2))
    var mul: BigInteger = BigInteger.valueOf(1)
    for(elem in arr) {
        var count = countTrees(elem, lines)
        mul *= count
    }
    println(mul.toString())
}

fun countTrees(elem: Pair<Int, Int>, lines: List<String>): BigInteger {
    var pos = elem.first
    var count: BigInteger = BigInteger.valueOf(0)
    for (i in elem.second until (lines.size) step elem.second) {
        if (lines[i][pos % lines[i].length] == '#') {
            ++count
        }
        pos += elem.first
    }
    return count
}