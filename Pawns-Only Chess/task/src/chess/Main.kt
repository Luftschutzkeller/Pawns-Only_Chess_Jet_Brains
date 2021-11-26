package chess

fun main() {
    println("Pawns-Only Chess\n" +
            "First Player's name:")

    val name1 = readLine()!!

    println("Second Player's name:")

    val name2 = readLine()!!
    var turn = name1
    val board = Array(8) { Array(8) { ' ' } }

    for (i in 0..7) board[6][i] = 'W'
    for (i in 0..7) board[1][i] = 'B'

    board.print()

    while (true) {
        println("$turn's turn:")

        val input = readLine()!!
        val color = if (turn == name1) "white" else "black"

        if (input == "exit") break

        if (!input.isValidInput()) println("Invalid Input") else {
            val x1 = input[0].code - 97
            val y1 = 8 - input[1].digitToInt()
            val x2 = input[2].code - 97
            val y2 = 8 - input[3].digitToInt()

            if (board[y1][x1] != color[0].uppercaseChar()) println("No $color pawn at ${input.substring(0..1)}") else {
                if (board.isValidMove(x1, y1, x2, y2, color)) {
                    board[y1][x1] = ' '
                    board[y2][x2] = color[0].uppercaseChar()
                    board.print()

                    turn = if (turn == name1) name2 else name1
                } else println("Invalid Input")
            }
        }
    }

    println("Bye!")
}

fun Array<Array<Char>>.print() {
    println("  +---+---+---+---+---+---+---+---+")

    this.forEachIndexed { i, row ->
        print("${8 - i} ")

        row.forEach { print("| $it ") }

        println("|\n" +
                "  +---+---+---+---+---+---+---+---+")
    }

    println("    a   b   c   d   e   f   g   h\n")
}

fun Array<Array<Char>>.isValidMove(x1: Int, y1: Int, x2: Int, y2: Int, color: String): Boolean {
    val oppositeColor = if (color == "white") "black" else "white"

    if (x1 != x2 || this[y2][x2] == oppositeColor[0].uppercaseChar()) return false

    val firstMove = color == "white" && y1 == 6 || color == "black" && y1 == 1

    return if (color == "white") {
        y1 - y2 == 1 || firstMove && y1 - y2 == 2
    } else {
        y2 - y1 == 1 || firstMove && y2 - y1 == 2
    }
}

fun String.isValidInput(): Boolean = Regex("[a-h][1-8][a-h][1-8]").matches(this)
