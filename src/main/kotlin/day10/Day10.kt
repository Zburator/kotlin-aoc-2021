package day10

import utils.readFile
import java.util.*


fun main() {
    val path = "./src/main/resources/day10/input.txt"
    println("Part 1 output " + part1(readFile(path)))

}

fun part1(text:List<String>): Int {
    var elemnt = Array(4){0}

    for ( i in text.indices) {
        var invalidCharacter = checkCorrectness(text[i])
        when (invalidCharacter) {
            ")" -> elemnt[0]= elemnt[0]+1
            "]" ->  elemnt[1]=elemnt[1]+1
            "}" ->  elemnt[2]=elemnt[2]+1
            ">" ->  elemnt[3]=elemnt[3]+1
        }
    }

    return (elemnt[0]*3)+(elemnt[1]*57)+(elemnt[2]*1197)+(elemnt[3]*25137)
}


fun checkCorrectness(line:String): String {
    var list = line.split("").filter { it != "" || it!= " " }.toMutableList()
    var stack = ArrayDeque<String>()
    for (i in list.indices) {
        when (list[i]) {
            "[" -> stack.push(list[i])
            "(" -> stack.push(list[i])
            "{" -> stack.push(list[i])
            "<" -> stack.push(list[i])
            ")" -> if(stack.pop() != "(") {
                return ")";
            }
            "]" -> if(stack.pop() != "[") {
                return "]";
            }
            "}" -> if(stack.pop() != "{") {
                return "}";
            }
            ">" -> if(stack.pop() != "<") {
                return ">";
            }
        }
    }
    return ""
}