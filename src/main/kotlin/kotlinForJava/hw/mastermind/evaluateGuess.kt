package kotlinForJava.hw.mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val rightPosition = secret.zip(guess).count { pair -> pair.first == pair.second }
    val commonCharsCount = "ABCDEF".sumBy { ch ->
        Math.min(secret.count { sCh -> sCh == ch }, guess.count { gCh -> gCh == ch })
    }
    return Evaluation(rightPosition, commonCharsCount - rightPosition)
}
