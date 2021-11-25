package chess

fun main() {
    println("Pawns-Only Chess\n" +
            "First Player's name:")

    val name1 = readLine()!!

    println("Second Player's name:")

    val name2 = readLine()!!

    println("  +---+---+---+---+---+---+---+---+\n" +
            "8 |   |   |   |   |   |   |   |   |\n" +
            "  +---+---+---+---+---+---+---+---+\n" +
            "7 | B | B | B | B | B | B | B | B |\n" +
            "  +---+---+---+---+---+---+---+---+\n" +
            "6 |   |   |   |   |   |   |   |   |\n" +
            "  +---+---+---+---+---+---+---+---+\n" +
            "5 |   |   |   |   |   |   |   |   |\n" +
            "  +---+---+---+---+---+---+---+---+\n" +
            "4 |   |   |   |   |   |   |   |   |\n" +
            "  +---+---+---+---+---+---+---+---+\n" +
            "3 |   |   |   |   |   |   |   |   |\n" +
            "  +---+---+---+---+---+---+---+---+\n" +
            "2 | W | W | W | W | W | W | W | W |\n" +
            "  +---+---+---+---+---+---+---+---+\n" +
            "1 |   |   |   |   |   |   |   |   |\n" +
            "  +---+---+---+---+---+---+---+---+\n" +
            "    a   b   c   d   e   f   g   h\n")

    var turn = name1

    while (true) {
        println("$turn's turn:")

        val input = readLine()!!

        if (input == "exit") break

        if (input.isValidMove()) turn = if (turn == name1) name2 else name1 else println("Invalid Input")
    }

    println("Bye!")
}

fun String.isValidMove(): Boolean = Regex("[a-h][1-8][a-h][1-8]").matches(this)
