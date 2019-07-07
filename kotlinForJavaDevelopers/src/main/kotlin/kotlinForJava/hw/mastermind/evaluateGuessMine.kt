package kotlinForJava.hw.mastermind

fun evaluateGuessMine(secret: String, guess: String): Evaluation {
    val rightPosition = secret.withIndex()
            .filter { indexedValue -> guess[indexedValue.index] == indexedValue.value}.size
    val secretCharListWithoutMatch = secret.withIndex()
            .filter { indexedValue -> guess[indexedValue.index] != indexedValue.value}
            .map { indexedValue -> indexedValue.value }
            .toList()
    val map = HashMap<Char, Int>()
    secretCharListWithoutMatch.forEach { ch ->
        if(map.containsKey(ch)) {
            map[ch] = map.getValue(ch) + 1
        } else {
            map[ch] = 1
        }
    }
    val guessCharListWithoutMatch = guess.withIndex()
            .filter { indexedValue -> secret[indexedValue.index] != indexedValue.value}
            .map { indexedValue -> indexedValue.value }
            .toList()
    var wrongPosition = 0
    guessCharListWithoutMatch.forEach { ch ->
        run {
            if (map.getOrDefault(ch, 0) > 0) {
                wrongPosition++
                map[ch] = map.getOrDefault(ch, 0) - 1
            }
        }
    }
    return Evaluation(rightPosition, wrongPosition)
}
