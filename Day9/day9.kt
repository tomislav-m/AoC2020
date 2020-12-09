import java.io.File
import java.math.BigInteger

fun main() {
    val input: List<BigInteger> = File("day9.txt").useLines { it.map { line -> line.toBigInteger() }.toList() }
    val res = part1(input)
    part2(input, res)
}

fun part1(input: List<BigInteger>): BigInteger {
    val preamble = 25
    var res: BigInteger = BigInteger.valueOf(0)

    for (i in preamble until input.size) {
        for (j in i - preamble until i) {
            if (input.subList(j, i).contains(input[i] - input[j])) {
                break
            }
            else {
                if (j + 1 == i) {
                    res = input[i]
                    println(res)
                    return res
                }
                continue
            }
        }
    }
    return res
}

fun part2(input: List<BigInteger>, num: BigInteger) {
    for (i in input.indices - 1) {
        var res = num
        for (j in i until input.size - 1) {
            res -= input[j]
            if (res == BigInteger.valueOf(0)) {
                println(input.subList(i, j + 1).minOf { it } + input.subList(i, j + 1).maxOf { it })
                return
            } else if (res > BigInteger.valueOf(0)) {
                continue
            } else {
                break
            }
        }
    }
}