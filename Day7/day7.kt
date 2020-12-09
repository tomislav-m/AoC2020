import java.io.File

fun main() {
    val input: List<String> = File("day7.txt").useLines { it.toList() }
    part1(input)
    part2(input)
}

fun part1(input: List<String>) {
    val containsMap = hashMapOf<String, MutableList<String>>()
    for (line in input) {
        val bag = line.substringBefore(" bags")
        val containBags = line.substringAfter("contain ").split(", ")
        for (containingBag in containBags) {
            val split = containingBag.split(" ")
            val bagName = split[1] + " " + split[2]
            if (containsMap.containsKey(bagName)) {
                val list = containsMap[bagName]
                list?.add(bag)
            } else {
                containsMap[bagName] = mutableListOf(bag)
            }
        }
    }
    val set = findBag("shiny gold", containsMap)
    println(set.size)
}

fun part2(input: List<String>) {
    val containsMap = hashMapOf<String, MutableList<Pair<String, Int>>>()
    for (line in input) {
        val bag = line.substringBefore(" bags")
        val containBags = line.substringAfter("contain ").split(", ")
        for (containingBag in containBags) {
            val split = containingBag.split(" ")
            val bagName = split[1] + " " + split[2]
            if (containsMap.containsKey(bag)) {
                val list = containsMap[bag]
                list?.add(Pair(bagName, split[0].toInt()))
            } else {
                try {
                    containsMap[bag] = mutableListOf(Pair(bagName, split[0].toInt()))
                } catch (e: Exception) {
                    println(containingBag)
                }
            }
        }
    }
    println(countBags("shiny gold", containsMap))
}

fun findBag(bagName: String, containsMap: Map<String, List<String>>): Set<String> {
    val set = mutableSetOf<String>()
    if (containsMap.containsKey(bagName)) {
        for (bag in containsMap[bagName]!!) {
            set.add(bag)
            set.addAll(findBag(bag, containsMap))
        }
    }
    return set
}

fun countBags(bagName: String, containsMap: Map<String, List<Pair<String, Int>>>): Int {
    var count = 0

    if (containsMap.containsKey(bagName)) {
        for (bag in containsMap[bagName]!!) {
            count += bag.second
            count += (bag.second * countBags(bag.first, containsMap))
        }
    }

    return count
}