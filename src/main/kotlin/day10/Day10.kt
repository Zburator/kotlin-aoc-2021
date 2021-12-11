package day10

import utils.readFile
import java.util.*


fun main() {
    val path = "./src/main/resources/day10/input.txt"
    println("Part 1 output " + part1(readFile(path)))
    println("Part 2 output " + part2(readFile(path)))

}

fun part1(text: List<String>): Int {
    var elemnt = Array(4) { 0 }

    for (i in text.indices) {
        var invalidCharacter = checkCorrectness(text[i])
        when (invalidCharacter) {
            ")" -> elemnt[0] = elemnt[0] + 1
            "]" -> elemnt[1] = elemnt[1] + 1
            "}" -> elemnt[2] = elemnt[2] + 1
            ">" -> elemnt[3] = elemnt[3] + 1
        }
    }

    return (elemnt[0] * 3) + (elemnt[1] * 57) + (elemnt[2] * 1197) + (elemnt[3] * 25137)
}


fun part2(text: List<String>): Long {
    var sum = mutableListOf<Long>()
    for (i in text.indices) {
        var pair = checkCorrectness2(text[i])
        if (pair.first == "") {
            sum.add(complete(pair.second))
        }
    }

    var sortedList = sum.sortedDescending()

    return sortedList[0 + (sortedList.size - 1) / 2]
}


fun checkCorrectness(line: String): String {
    var list = line.split("").filter { it != "" || it != " " }.toMutableList()
    var stack = ArrayDeque<String>()
    for (i in list.indices) {
        when (list[i]) {
            "[" -> stack.push(list[i])
            "(" -> stack.push(list[i])
            "{" -> stack.push(list[i])
            "<" -> stack.push(list[i])
            ")" -> if (stack.pop() != "(") {
                return ")";
            }
            "]" -> if (stack.pop() != "[") {
                return "]";
            }
            "}" -> if (stack.pop() != "{") {
                return "}";
            }
            ">" -> if (stack.pop() != "<") {
                return ">";
            }
        }
    }
    return ""
}


fun complete(stack: ArrayDeque<String>): Long {
    var sum: Long = 0
    while (stack.size > 0) {
        sum *= 5
        sum += completeString(stack.pop())
    }

    return sum

}

fun completeString(startingChar: String): Int {
    when (startingChar) {
        "[" -> return 2
        "(" -> return 1
        "{" -> return 3
        "<" -> return 4
    }

    return 0;
}


fun checkCorrectness2(line: String): Pair<String, ArrayDeque<String>> {
    var list = line.split("").filter { it != "" || it != " " }.toMutableList()
    var stack = ArrayDeque<String>()
    for (i in list.indices) {
        when (list[i]) {
            "[" -> stack.push(list[i])
            "(" -> stack.push(list[i])
            "{" -> stack.push(list[i])
            "<" -> stack.push(list[i])
            ")" -> if (stack.pop() != "(") {
                return Pair(")", stack)
            }
            "]" -> if (stack.pop() != "[") {
                return Pair("]", stack);
            }
            "}" -> if (stack.pop() != "{") {
                return Pair("}", stack);
            }
            ">" -> if (stack.pop() != "<") {
                return Pair(">", stack);
            }
        }
    }
    return Pair("", stack)
}