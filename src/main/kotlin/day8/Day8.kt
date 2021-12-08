package day8

import utils.readFile


fun main() {
    val path = "./src/main/resources/day8/input.txt"
    var text = readFile(path)
    println("Part 1 output " + part1(text))
    println("Part 2 output " + part2(text))
}

fun part1(text:List<String>) :Int {


    var testList = mutableListOf<String>()

    for (i in text.indices) {
        var splited = text[i].split("|").filter { it != "" || it != " " }
        var tempList = splited[1].split(" ").filter { it != "" || it != " " }
        testList.addAll(tempList)
    }


    return testList.filter { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }.size;
}

fun part2(text:List<String>) :Int {


    var sum = 0;
    var ans = ""
    for (i in text.indices) {
        var testList = mutableListOf<String>()

        var splited = text[i].split("|").filter { it != "" || it != " " }
        var tempList1 = splited[0].split(" ").filter { it != "" && it != " " }
        var tempList2 = splited[1].split(" ").filter { it != "" && it != " " }
        testList.addAll(tempList1)
        testList.addAll(tempList2)
        var map = createMap(testList)
        tempList2.forEach {
            var p = it.toCharArray().sorted().joinToString("");
            var t = map.indexOf(p);
            ans += t
        }
        sum += ans.toInt()
        ans = ""
    }

    return sum
}


fun createMap(list: List<String> ) : List<String> {
    var mixedMap = mutableListOf<String>()
    for(i in 0..9) {
        mixedMap.add("")
    }

    var temp = list.map { it.toCharArray()
        .sorted()
        .joinToString("") }
        .sortedBy { it.length }
    temp = temp.distinct()
     for (i in temp.indices) {

         when(temp[i].length) {
             2-> mixedMap[1] = temp[i]
             3-> mixedMap[7] = temp[i]
             4-> mixedMap[4] = temp[i]
             7-> mixedMap[8] = temp[i]
             5 -> {
                 if (temp[i].toList().intersect(mixedMap[1].toList()).size == 2) {
                     mixedMap[3] = temp[i]
                 } else if ( temp[i].toList().intersect(mixedMap[4].toList()).size == 3) {
                     mixedMap[5] =  temp[i] // a 5
                 } else {
                     mixedMap[2] =  temp[i] // a 2
                 }
             }

             6 -> {
                 if (temp[i].toList().intersect(mixedMap[1].toList()).size < 2) {
                     mixedMap[6] = temp[i] // a 6
                 } else if (temp[i].toList().intersect(mixedMap[4].toList()).size == 4) {
                     mixedMap[9] = temp[i] // a 9
                 } else {
                     mixedMap[0] = temp[i] // a 0
                 }
             }
         }
     }

    return mixedMap;
}