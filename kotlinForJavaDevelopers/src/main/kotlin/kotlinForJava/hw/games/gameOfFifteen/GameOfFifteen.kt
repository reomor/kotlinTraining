package kotlinForJava.hw.games.gameOfFifteen

import kotlinForJava.hw.board.Direction
import kotlinForJava.hw.board.GameBoard
import kotlinForJava.hw.board.createGameBoard
import kotlinForJava.hw.games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
    object : Game {

        private val board: GameBoard<Int> = createGameBoard(4)

        override fun initialize() {
            val permutation = initializer.initialPermutation
            for ((index, cell) in board.getAllCells().withIndex()) {
                board[cell] = if (index < permutation.size) {
                    permutation[index]
                } else null
            }
        }

        override fun canMove(): Boolean {
            return true
        }

        override fun hasWon(): Boolean {
            return board.getAllCells()
                .withIndex()
                .all { (index, cell) ->
                    val cellValue = board[cell]
                    if (cellValue != null) {
                        cellValue == index + 1
                    } else {
                        true
                    }
                }

        }

        override fun processMove(direction: Direction) {
            val reversed = direction.reversed()
            with(board) {
                val nullableCell = filter { it == null }.first()
                val neighbourCell = nullableCell.getNeighbour(reversed)
                // if reversed neighbour exist - swap
                if (neighbourCell != null) {
                    board[nullableCell] = board[neighbourCell]
                        .also { board[neighbourCell] = board[nullableCell] }
                }
            }
        }

        override fun get(i: Int, j: Int): Int? {
            return board[board.getCell(i, j)]
        }
    }

