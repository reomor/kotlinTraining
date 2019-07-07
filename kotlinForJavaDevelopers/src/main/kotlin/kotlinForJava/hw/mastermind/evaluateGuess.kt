package kotlinForJava.hw.mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val rightPosition = secret.zip(guess).count { it.first == it.second }
    val commonCharsCount = "ABCDEF".sumBy { ch ->
        Math.min(secret.count { it == ch }, guess.count { it == ch })
    }
    return Evaluation(rightPosition, commonCharsCount - rightPosition)
}
