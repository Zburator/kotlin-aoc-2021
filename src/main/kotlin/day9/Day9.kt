package day9

import utils.readFile

fun main() {
    val path = "./src/main/resources/day9/input.txt"
    var text = readFile(path)

    print("Part 1 output " + part1(text));
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
                sumMin += mat[i][j]+1
            }
        }
    }



    return sumMin;

}


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
