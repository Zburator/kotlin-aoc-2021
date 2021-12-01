package utils

import java.io.BufferedReader
import java.io.File

fun readFile(path: String): List<String> {
    val bufferedReader: BufferedReader = File(path).bufferedReader()
    return bufferedReader.readLines();
}