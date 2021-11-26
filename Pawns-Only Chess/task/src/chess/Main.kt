package chess
import kotlin.math.abs

val board = Array(8) { Array(8) { ' ' } }
val whitePawns = Array(8) { Pawn("white", it, 6) }
val blackPawns = Array(8) { Pawn("black", it, 1) }

fun main() {
    println("Pawns-Only Chess\n" +
            "First Player's name:")

    val name1 = readLine()!!

    println("Second Player's name:")

    val name2 = readLine()!!
    var turn = name1

    whitePawns.forEach { board[it.y][it.x] = it.color.first().uppercaseChar() }
    blackPawns.forEach { board[it.y][it.x] = it.color.first().uppercaseChar() }

    printBoard()

    while (true) {
        println("$turn's turn:")

        val input = readLine()!!

        if (input == "exit") break

        val color = if (turn == name1) "white" else "black"

        if (!input.isValidInput()) println("Invalid Input") else {
            val x1 = input[0].code - 97
            val y1 = 8 - input[1].digitToInt()
            val x2 = input[2].code - 97
            val y2 = 8 - input[3].digitToInt()

            if (board[y1][x1] != color[0].uppercaseChar()) println("No $color pawn at ${input.substring(0..1)}") else {
                if (isValidMove(x1, y1, x2, y2, color)) {
                    board[y1][x1] = ' '
                    board[y2][x2] = color[0].uppercaseChar()

                    getPawnAt(x1, y1).x = x2
                    getPawnAt(x2, y1).y = y2

                    printBoard()

                    if (color == "white") {
                        blackPawns.forEach { if (it.y != 1) it.firstMove = false}
                    } else {
                        whitePawns.forEach { if (it.y != 6) it.firstMove = false}
                    }

                    turn = if (turn == name1) name2 else name1
                } else println("Invalid Input")
            }
        }
    }

    println("Bye!")
}

class Pawn(val color: String, var x: Int, var y: Int, var captured: Boolean = false, var firstMove: Boolean = true)

fun getPawnAt(x: Int, y: Int): Pawn {
    whitePawns.forEach { if (it.x == x && it.y == y) return it }
    blackPawns.forEach { if (it.x == x && it.y == y) return it }

    return Pawn("none", -1, -1)
}

fun printBoard() {
    println("  +---+---+---+---+---+---+---+---+")

    board.forEachIndexed { i, row ->
        print("${8 - i} ")

        row.forEach { print("| $it ") }

        println("|\n" +
                "  +---+---+---+---+---+---+---+---+")
    }

    println("    a   b   c   d   e   f   g   h\n")
}

fun String.isValidInput(): Boolean = Regex("[a-h][1-8][a-h][1-8]").matches(this)

fun isValidMove(x1: Int, y1: Int, x2: Int, y2: Int, turn: String): Boolean {
    val oppositeColor = if (turn == "white") "black" else "white"
    val yC1 = if (turn == "white") y1 else y2
    val yC2 = if (turn == "white") y2 else y1
    val enPassantRow = if (turn == "white") 3 else 4

    if (x1 == x2) {
        if (board[y2][x2] == oppositeColor[0].uppercaseChar()) return false

        return yC1 - yC2 == 1 || getPawnAt(x1, y1).firstMove && yC1 - yC2 == 2
    } else {
        if (yC1 - yC2 != 1 || abs(x1 - x2) != 1) return false

        if (getPawnAt(x2, y2).color == oppositeColor) {
            getPawnAt(x2, y2).captured = true

            return true
        }

        if (y1 == enPassantRow && getPawnAt(x2, y1).color == oppositeColor && getPawnAt(x2, y1).firstMove) {
            getPawnAt(x2, y1).captured = true

            board[y1][x2] = ' '

            return true
        }
    }

    return false
}