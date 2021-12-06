package day6

import utils.readFile

fun main() {
    val path = "./src/main/resources/day6/input.txt"
    var text = readFile(path)
    var input= text[0].split(",").filter{it!=""}.map { it.toInt() }
//    println("Part 1 output " + simulate(input, 18))
    println("Part 1 output " + simulate(input, 80))
    println("Part 2 output " + simulate(input, 256))


}

fun simulate(list: List<Int>, steps:Int) : Long {

    var map = createMap(list)

    for(i in 0 until steps) {
        var zeroElements = map.getOrDefault(0,0)
        var newMap = map
            .filterKeys { key -> key > 0 }
            .mapKeys { kv -> kv.key - 1 }
            .toMutableMap()


        if(zeroElements >0) {
            newMap[8] = newMap.getOrDefault(8,0)+zeroElements
            newMap[6] = newMap.getOrDefault(6,0)+zeroElements
        }
        map = newMap
    }

    return map.values.sum()

}

fun createMap(list: List<Int>) : Map<Int, Long> {
    var map = mutableMapOf<Int, Long>()

    for (i in list.indices) {
        var n = list[i]
        map[n] = map.getOrDefault(n,0) +1
    }

    return map
}