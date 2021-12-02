package day2

import utils.readFile


fun main() {
    val path = "./src/main/resources/day2/input.txt"
    println("Part 1 output " + part1(readFile(path)))
    println("Part 2 output " + part2(readFile(path)))
}


fun part1(text: List<String>): Int {
    var depth = text.map { parseDepth(it) }.sum()
    var forward = text.map { parseForward(it)}.sum()

    return depth*forward
}

fun parseDepth(line:String) : Int {
    var splited = line.split(" ")
    return when(splited[0]) {
        "up" ->  -splited[1].toInt()
        "down" ->  splited[1].toInt()
        else -> 0
    }
}

fun parseForward(line:String) : Int {
    var splited = line.split(" ")
    return when(splited[0]) {
        "forward" ->  splited[1].toInt()
        else -> 0
    }
}


fun part2(text: List<String>): Int {
    var aim = 0
    var depth = 0
    var horizontal = 0
    val tuples = createTuples(text)


    tuples.forEach {
        if (it.first.equals("forward")) {
            horizontal += it.second;
            depth += it.second * aim;
        } else {
            aim += it.second
        }
    }

    return depth*horizontal;
}


fun createTuples(text: List<String>): List<Pair<String, Int>>{
    return text.map { createTuple(it)}
}

fun createTuple(line: String): Pair<String, Int> {
    var splitted = line.split(" ")
    var value = 0;
    when(splitted[0]) {
        "up" -> value = -splitted[1].toInt()
        else -> value = splitted[1].toInt() }
    return Pair(splitted[0], value)
}

