package kotlinForJava.hw.games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    val visited = mutableSetOf<Int>()
    var numberOfCycles = 0
    val correction = if (permutation.contains(0)) 0 else 1
    for (i in IntRange(correction, permutation.size - 1 + correction)) {
        if (i !in visited) {
            //val cycle = mutableListOf<Int>()
            var current = permutation[i - correction]
            //cycle += current
            visited += current
            while (current != i) {
                current = permutation[current - correction]
                //cycle += current
                visited += current
            }
            numberOfCycles++
        }
    }
    return (permutation.size - numberOfCycles) % 2 == 0
}

fun main() {
    println(isEven(listOf(5, 3, 4, 2, 1)))
    println(isEven(listOf(5, 4, 3, 2, 1)))
    println(isEven(listOf(0, 4, 3, 2, 1)))
}
