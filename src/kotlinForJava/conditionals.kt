package kotlinForJava

import kotlinForJava.Color.*; //no need to add Color.BLUE

enum class Color {
    RED, GREEN, BLUE
}

open class Pet {}

class Cat : Pet() {
    fun meow(): String = "meow"
}

class Dog : Pet() {
    fun woof(): String = "woof"
}


fun main() {
    // local fun will not conflict with static in another file
    fun max(a: Int, b: Int): Int {
        // just to show if expression
        val max = if (a > b) a else b
        return max
    }

//        fun getDescription(color: Color): String =
//        when (color) {
//            Color.RED -> "hot"
//            Color.GREEN -> "mild"
//            Color.BLUE -> "cold"
//        }
    fun getDescription(color: Color): String =
        when (color) {
            RED -> "hot"
            GREEN -> "mild"
            BLUE -> "cold"
        }

    fun respondToInput(input: String) = when (input) {
        "y", "yes" -> "answer is yes"
        "n", "no" -> "answer is no"
        else -> "i don't understand"
    }

    fun mix(c1: Color, c2: Color): Int =
        when (setOf(c1, c2)) {
            setOf(RED, GREEN) -> 2
            setOf(BLUE, GREEN, RED) -> 3
            else -> 0
        }

    //since 1.3 allowed to inline val in when()
    when (val pet = getAnyPet()) {
        is Cat -> pet.meow() //already casted to Cat
        is Dog -> pet.woof()
    }

    fun updateWeather(degrees: Int) {
        val (description, color) = when {
            degrees < 5 -> "cold" to BLUE //return Pair
            else -> "" to GREEN
        }
    }
}

fun getAnyPet(): Pet {
    return Dog()
}
