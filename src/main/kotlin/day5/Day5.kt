package day5

import utils.readFile



class Line {
    var x1 = 0
    var y1 = 0
    var x2 = 0
    var y2 = 0

    fun isHorizontal() :Boolean {
        return  x1==x2
    }

    fun isVertical() :Boolean {
        return  y1==y2
    }
}

fun main() {
    val path = "./src/main/resources/day5/input.txt"
    println("Part 1 output " + part1(readFile(path)))
//    println("Part 2 output " + part2(readFile(path)))
}

fun part1(text: List<String>): Int {
    var linelIst = mutableListOf<Line>()
    var sizeX =0;
    var sizeY =0;
    for(i in text.indices) {
        var line = toLine(text[i])
        if(line.x1 > sizeX) {
            sizeX  = line.x1
        }

        if(line.x2 > sizeX) {
            sizeX  = line.x2
        }

        if(line.y1 > sizeY) {
            sizeY  = line.y1
        }

        if(line.y2 > sizeY) {
            sizeY  = line.y2
        }
        linelIst.add(line)
    }

    linelIst = linelIst.filter { it.isHorizontal() || it.isVertical() } as MutableList<Line>
    var mat = Array(sizeX+1) { Array(sizeY+1) { 0 } }


    for (i in linelIst.indices) {
        var line = linelIst[i]
        if(line.isVertical()) {
            var t = invert(line.x1, line.x2)
            for (j in t.first..t.second) {
                mat[j][line.y1] += 1
            }
        }
        if(line.isHorizontal()) {
            var t = invert(line.y1, line.y2)
            for (j in t.first..t.second) {
                mat[line.x1][j] += 1
            }
        }
    }

    var multipleLines = 0;
        for(r in 0..sizeX) {
            for(c in 0..sizeY) {
                if(mat[r][c] >1) {
                    multipleLines++;
                }
            }
        }

    return multipleLines;

}

fun toLine(text: String) : Line {
    var split = text.split("->")
    var firstPart = split[0].trim().split(",")
    var secondPard = split[1].trim().split(",")
    var line =  Line()
    line.x1 = firstPart[0].toInt()
    line.y1 = firstPart[1].toInt()
    line.x2 = secondPard[0].toInt()
    line.y2 = secondPard[1].toInt()
    return line
}

fun invert(a: Int, b: Int) :Pair<Int, Int> {
    if (a > b) {
        return Pair(b,a)
    }
    return Pair(a,b)
}

