package kotlinForJava;

import java.io.IOException
import java.lang.IllegalArgumentException

@Throws(IOException::class) //call it from Java-code with try(){}catch(){} compiles
fun foo() {
    throw IOException()
}

fun main() {
    val number = 101
    val percentage = if (number in 0..100) {
        number
    } else {
        throw IllegalArgumentException("illegal percentage value: $number")
    }

    val string: String = ""
    val numberFromString = try {
        Integer.parseInt(string)
    } catch (e: NumberFormatException) {
        return
    }
}
