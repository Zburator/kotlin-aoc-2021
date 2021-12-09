package day9

import utils.readFile

val path = "./src/main/resources/day9/input.txt"

fun main() {
    var text = readFile(path)

    println("Part 1 output " + part1(text));
    println("Part 1 output " + part2(text));


}


fun part1(text: List<String>): Int {
    var mat = Array(text.size) { Array(text[0].length) { 9 } }

    for (i in text.indices) {
        var row = text[i].split("").filter { it != "" }.map { it.toInt() }
        for (j in row.indices) {
            mat[i][j] = row[j]
        }
    }

    var sumMin = 0
    for (i in mat.indices) {
        for (j in mat[i].indices) {
            if (isMin(mat, i, j)) {
                sumMin += mat[i][j] + 1
            }
        }
    }


    return sumMin;
}

var mat = Array(100) { Array(100) { 9 } }
fun part2(text: List<String>): Int {


    for (i in text.indices) {
        var row = text[i].split("").filter { it != "" }.map { it.toInt() }
        for (j in row.indices) {
            mat[i][j] = row[j]
        }
    }

    var lowPoints = mutableListOf<Point>()
    var pointMap = mutableListOf<List<Point>>()

    for (i in mat.indices) {
        var t = mutableListOf<Point>()
        for (j in mat[i].indices) {
            if (isMin(mat, i, j)) {
                lowPoints.add(Point(i, j, mat[i][j]))
            }
            t.add(Point(i, j, mat[i][j]))
        }
        pointMap.add(t)
    }

    var basinList = mutableListOf<List<Point>>()


    for (i in lowPoints.indices) {
        var basin = searchBasin(lowPoints[i])
        basinList.add(basin)
    }

    basinList.sortByDescending { it.size }
    var mul = 1;
    for (i in 0..2) {
        mul *= basinList[i].size
    }

    return mul
}


fun searchBasin(point: Point): List<Point> {
    var basinList = mutableListOf<Point>()
    basinList.add(point)
    findBasin(point, basinList);
    return basinList
}

fun findBasin(point: Point, basin: MutableList<Point>) {

    if (point.y >= 1 && mat[point.x][point.y - 1] < 9) {
        var p = Point(point.x, point.y - 1, mat[point.x][point.y - 1])
        if(doesntContain(basin,p)) {
            basin.add(p)
            findBasin(p, basin)
        }
    }
    if (point.y <= mat[0].size - 2 && mat[point.x][point.y + 1] < 9) {
        var p = Point(point.x, point.y + 1, mat[point.x][point.y + 1])
        if(doesntContain(basin,p)) {
            basin.add(p)
            findBasin(p, basin)
        }
    }
    if (point.x >= 1 && mat[point.x - 1][point.y] < 9) {
        var p = Point(point.x - 1, point.y, mat[point.x - 1][point.y])
        if(doesntContain(basin,p)) {
            basin.add(p)
            findBasin(p, basin)
        }
    }
    if (point.x <= mat.size - 2 && mat[point.x + 1][point.y] < 9) {
        var p = Point(point.x + 1, point.y, mat[point.x + 1][point.y])
        if(doesntContain(basin,p)) {
            basin.add(p)
            findBasin(p, basin)
        }
    }
}


fun doesntContain(list: List<Point>, point: Point): Boolean {
    var ok = true
    list.forEach {
        ok = ok && (it.x != point.x
                || it.y != point.y)

    }

    return ok

}

data class Point(val x: Int, val y: Int, val value: Int)


fun isMin(mat: Array<Array<Int>>, i: Int, j: Int): Boolean {

    if (i == 0) {
        if (j == 0) {
            if (mat[i][j] < mat[i + 1][j] && mat[i][j] < mat[i][j + 1]) {
                return true
            }
            return false
        }

        if (j == mat[0].size - 1) {
            if (mat[i][j] < mat[i + 1][j] && mat[i][j] < mat[i][j - 1]) {
                return true
            }

            return false
        }

        if (j < mat[0].size - 2 && mat[i][j] < mat[i + 1][j] && mat[i][j] < mat[i][j - 1] && mat[i][j] < mat[i][j + 1]) {
            return true
        }

        return false
    }


    if (i == mat.size - 1) {
        if (j == 0) {
            if (mat[i][j] < mat[i - 1][j] && mat[i][j] < mat[i][j + 1]) {
                return true
            }
            return false
        }

        if (j == mat[0].size - 1) {
            if (mat[i][j] < mat[i - 1][j] && mat[i][j] < mat[i][j - 1]) {
                return true
            }

            return false
        }

        if (j < mat[i].size - 2 && mat[i][j] < mat[i - 1][j] && mat[i][j] < mat[i][j - 1] && mat[i][j] < mat[i][j + 1]) {
            return true
        }
        return false
    }

    if (j == 0) {
        if (mat[i][j] < mat[i - 1][j] && mat[i][j] < mat[i + 1][j] && mat[i][j] < mat[i][j + 1]) {
            return true
        }
        return false
    }


    if (j == mat[i].size - 1) {
        if (mat[i][j] < mat[i][j - 1] && mat[i][j] < mat[i - 1][j] && mat[i][j] < mat[i + 1][j]) {
            return true
        }
        return false
    }

    if (mat[i][j] < mat[i][j - 1] && mat[i][j] < mat[i][j + 1] &&
        mat[i][j] < mat[i + 1][j] && mat[i][j] < mat[i - 1][j]
    ) {
        return true
    }


    return false
}
