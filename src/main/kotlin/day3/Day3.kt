package day3


import utils.readFile

fun main() {
    val path = "./src/main/resources/day3/input.txt"
    println("Part 1 output " + part1(readFile(path)))
    println("Part 2 output " + part2(readFile(path)))
}


fun part1(text: List<String>): Int {
    var frequencyList = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    text.map {
        it.split("")
            .filter { it != "" }
            .mapIndexed { index, s ->
                frequencyList[index] += s.toInt();
            }
    }

    var gamma = ""
    var epsilon = ""
    var totalNumbers = text.size
    for (s in frequencyList.indices) {
        if (totalNumbers - frequencyList[s] > frequencyList[s]) {
            gamma += "0"
            epsilon += "1"
        } else {
            gamma += "1"
            epsilon += "0"
        }
    }

    val gamaInt = gamma.toInt(2)
    val epsilonInt = epsilon.toInt(2)

    return gamaInt * epsilonInt
}


fun part2(text: List<String>): Int {
    var oxigen = getOxigen(text).toInt(2)
    var co2 = getCo2(text).toInt(2)
    return oxigen * co2
}

fun getOxigen(text: List<String>): String {
    var lines = text
    var index = 0
    while (lines.size > 1 && index < 12) {
        var temp = 0;

        lines.map {
            var t = it.split("")
                .filter { it != "" }

            temp += t[index].toInt()
        }

        if (temp >= lines.size - temp) {
            //filtering for bit on pozition index
            lines = lines.filter { it.split("").filter { it != "" }[index] == "1" }
        } else {
            lines = lines.filter { it.split("").filter { it != "" }.get(index) == "0" }
        }
        index += 1
    }

    return lines[0]
}

fun getCo2(text: List<String>): String {
    var lines = text
    var index = 0
    while (lines.size > 1 && index < 12) {
        var temp = 0;

        lines.map {
            var t = it.split("")
                .filter { it != "" }

            temp += t[index].toInt()
        }

        if (temp < lines.size - temp) {
            //filtering for bit on pozition index
            lines = lines.filter { it.split("").filter { it != "" }[index] == "1" }
        } else {
            lines = lines.filter { it.split("").filter { it != "" }[index] == "0" }
        }
        index += 1
    }

    return lines[0]
}
