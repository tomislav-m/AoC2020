import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("day6.txt").inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText().trim() }
    val input = inputString.split("\n\n")
    part1(input)
    part2(input)
}

fun part1(input: List<String>) {
    println(input.map { it.replace("\n", "").toSet().size }.sum())
}

fun part2(input: List<String>) {
    println(input.map { it.split("\n").reduce { acc, s -> acc.toCharArray().intersect(s.asIterable()).joinToString("")}.length }.sum())
}