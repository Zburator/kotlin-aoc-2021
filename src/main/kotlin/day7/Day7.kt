package day7

import utils.readFile


fun main() {
    val path = "./src/main/resources/day7/input.txt"
    var text = readFile(path)
    var input= text[0].split(",").filter{it!=""}.map { it.toInt() }

    println("Part 1 output " +cheapestFuelPosition(input))
    println("Part 2 output " +part2CheapestFuelProgresion(input))

}

fun createMap(list: List<Int>) : Map<Int, Long> {
    var map = mutableMapOf<Int, Long>()

    for (i in list.indices) {
        var n = list[i]
        map[n] = map.getOrDefault(n,0) +1
    }

    return map
}


fun cheapestFuelPosition(list: List<Int> ) : Long {
    var map = createMap(list)
    var distanteMap = mutableMapOf<Int, Long>()

    for (i in map.keys) {

         var totalFuel = map.filterKeys { it!=i }
            .map { entry -> Math.abs(entry.key - i)* entry.value }
            .sum()

        distanteMap[i] = totalFuel
    }

    return distanteMap.minBy { entry -> entry.value }?.value!!
}


fun part2CheapestFuelProgresion(list: List<Int> ) : Long {
    var map = createMap(list)
//    var distanteMap = mutableMapOf<Int, Long>()
    var distanceMap = mutableMapOf<Int,Long>()
    var maxKey = map.maxBy { it.key }!!.key
    for(i in 1..maxKey) {
        distanceMap[i] =0;
    }
    for (i in distanceMap.keys) {

        var totalFuel = map.filterKeys { it!=i }
            .map { entry -> progresion(Math.abs(entry.key - i))*entry.value}
            .sum()

        distanceMap[i] = totalFuel
    }

    return distanceMap.minBy { entry -> entry.value }?.value!!
}

fun progresion(max: Int) : Int {
    var sum = (1..max).sum()
    return sum
}