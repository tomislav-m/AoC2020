import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("day4.txt").inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText().trim() }
    val lines = inputString.split("\n\n")
    part1(lines)
    part2(lines)
}

fun part1(lines: List<String>) {
    val fields = arrayOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    var validCount = 0
    for (line in lines) {
        if (line.containsAll(fields)){
            ++validCount
        }
    }
    println(validCount)
}

fun part2(lines: List<String>) {
    val fields = arrayOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    var validCount = 0
    for (line in lines) {
        if (line.containsAll(fields)) {
            var valid = true
            for (field in line.split(" ", "\n")) {
                if (!field.isValid()) {
                    valid = false
                    break
                }
            }
            if (valid) {
                ++validCount
            }
        }
    }
    println(validCount)
}

fun String.containsAll(strings: Array<String>): Boolean {
    for (str in strings) {
        if (!this.contains(str  )) {
            return false
        }
    }
    return true
}

fun String.isValid(): Boolean {
    val input = this.split(":")
    if (input.size < 2) return false
    val value = input[1]
    when (input[0]) {
        "byr" -> {
            val year = value.toInt()
            (year < 1920 || year > 2002) && return false
        }
        "iyr" -> {
            val year = value.toInt()
            (year < 2010 || year > 2020) && return false
        }
        "eyr" -> {
            val year = value.toInt()
            (year < 2020 || year > 2030) && return false
        }
        "hgt" -> {
            if (value.contains("cm")) {
                val cm = value.substringBefore("cm").toInt()
                if (cm < 150 || cm > 193) return false
            } else if (value.contains("in")) {
                val inch = value.substringBefore("in").toInt()
                if (inch < 59 || inch > 76) return false
            } else return false
        }
        "hcl" -> {
            if (!value.matches(Regex("^#([0-9]|[a-f]){6}\$"))) return false
        }
        "ecl" -> {
            if (value !in arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")) return false
        }
        "pid" -> {
            if (!value.contains(Regex("^\\d{9}$"))) return false
        }
        "cid" -> return true
        else -> return false
    }
    return true
}