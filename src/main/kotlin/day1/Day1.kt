package day1

import java.io.BufferedReader
import java.io.File

fun main() {
    val path = "./src/main/resources/day1/input1.txt"
    println("Part 1 output " + part1(readFile(path)))
    println("Part 2 output " + part2(readFile(path)))

}

fun readFile(path: String): List<String> {
    val bufferedReader: BufferedReader = File(path).bufferedReader()
    return bufferedReader.readLines();
}

fun part1(text: List<String>): Int {
    return text.map { it.toInt() }
        .zipWithNext()
        .filter { (a, b) -> a < b }
        .count()
}

fun part2(text: List<String>): Int {
    return text.map { it.toInt() }.windowed(size = 3, step = 1)
        .map { (a, b, c) -> a + b + c }
        .zipWithNext().filter { (a, b) -> a < b }
        .count()
}



