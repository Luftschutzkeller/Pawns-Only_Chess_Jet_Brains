package chess

fun main() {
    println("Pawns-Only Chess")
    println(board())
}

fun board(): String {
    val empty = " "
    val black = "B"
    val white = "W"
    var board: String = ""
    val line = "  +---+---+---+---+---+---+---+---+\n"
    for (i in 8 downTo 1) {
        board += line
        board += "$i |"
        for (j in 1..8) {
            board += when (i) {
                7 -> " $black |"
                2 -> " $white |"
                else -> " $empty |"
            }
        }
        board += "\n"
    }
    board += line
    board += "    a   b   c   d   e   f   g   h"

    return board
}