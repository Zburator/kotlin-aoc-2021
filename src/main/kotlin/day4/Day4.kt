package day4

import utils.readFile


fun main() {
    val path = "./src/main/resources/day4/test.txt"
    println("Part 1 output " + part1(readFile(path)))
    println("Part 2 output " + part2(readFile(path)))
}

class BingoTicket {
    var ticketNumbers = Array(5) { Array(5) { 0 } }
    var binaryTicket = Array(5) { Array(5) { false } }
    var bingo = false
    var bingoPosition: Pair<Int, Boolean>? =null

    fun setLine(index: Int, line: String) {
        var numbers = line.split(" ")
            .filter { it != "" }
            .map { it.toInt() }

        for (j in numbers.indices) {
            ticketNumbers[index][j] = numbers[j]
        }
    }

    fun markNumber(number: Int) {
        for (i in 0..4) {
            for (j in 0..4) {
                if (ticketNumbers[i][j] == number) {
                    binaryTicket[i][j] = true
                    bingo = callBingo(i, j)
                }
            }
        }
    }


    fun callBingo(row: Int, column: Int): Boolean {
        var bingo = true;
        binaryTicket[row].map { v -> bingo = bingo && v }
        if (bingo) {
            bingoPosition = Pair(row, false)
            return bingo
        }
        bingo = true
        for (i in 0..4) {
            bingo = bingo && binaryTicket[i][column]
        }
        if (bingo) {
            bingoPosition = Pair(column, true)
            return bingo
        }
        return bingo
    }


}

fun updateTickets(number: Int, tickets: List<BingoTicket>): Pair<Boolean, Int> {
    for (i in tickets.indices) {
        tickets[i].markNumber(number);
        if (tickets[i].bingo) {
            return Pair(true, i)
        }
    }

    return return Pair(false, -1);
}

fun updateAll(number: Int, tickets: List<BingoTicket>): Pair<Boolean, Int> {
    for (i in tickets.indices) {
        tickets[i].markNumber(number);
    }

    for (i in tickets.indices) {
        if(tickets[i].bingo) {
            return Pair(true, i)
        }
    }

    return  Pair(false, -1);
}


fun computeValues(ticket: BingoTicket, lastCalled: Int): Int {
    var tempTicket = ticket;
    var sum = 0;
    for (i in 0..4) {
        for (j in 0..4) {
            if (!tempTicket.binaryTicket[i][j]) {
                sum += tempTicket.ticketNumbers[i][j]
            }
        }
    }


    return sum * lastCalled;
}

fun part1(text: List<String>): Int {

    var firstLine = text[0].split(",")
    var bingoTickets = mutableListOf<BingoTicket>()

    var tempTicket = BingoTicket()
    var index = 0
    for (i in 2..text.size-1) {
        if (text[i] == "") {
            bingoTickets.add(tempTicket)
            index = 0;
            tempTicket = BingoTicket()
        } else {
            tempTicket.setLine(index, text[i])
            index++
        }
    }
    bingoTickets.add(tempTicket)
    bingoTickets.reverse()
    for (s in firstLine.indices) {
        var number = firstLine[s].toInt()
        var bingo = updateTickets(number, bingoTickets)
        if (bingo.first) {
            return computeValues(bingoTickets[bingo.second], number)
        }

    }


    return 0
}

fun part2(text: List<String>): Int {

    var firstLine = text[0].split(",")
    var bingoTickets = mutableListOf<BingoTicket>()

    var tempTicket = BingoTicket()
    var index = 0
    for (i in 2..text.size - 1) {
        if (text[i] == "") {
            bingoTickets.add(tempTicket)
            index = 0;
            tempTicket = BingoTicket()
        } else {
            tempTicket.setLine(index, text[i])
            index++
        }
    }
    bingoTickets.add(tempTicket)
    bingoTickets.reverse()
    var winningList = mutableListOf<BingoTicket>()
    var lastToWin = BingoTicket()
    var lastNumber = 0
    for (s in firstLine.indices) {
        var number = firstLine[s].toInt()
        var bingo = updateAll(number, bingoTickets)
        winningList = bingoTickets.filter { it.bingo == true } as MutableList<BingoTicket>
        bingoTickets = bingoTickets.filter { it.bingo == false } as MutableList<BingoTicket>
        if (bingo.first) {
            lastToWin = winningList.get(winningList.size-1)
            lastNumber = number;
        }
        if(bingoTickets.size ==0) {
            break;
        }

    }

    return computeValues(lastToWin, lastNumber)
}


