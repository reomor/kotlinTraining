package kotlinForJava

import java.lang.StringBuilder

//fun String.lastChar(): Char = this.get(this.length - 1)
fun String.lastChar(): Char = get(length - 1) //without this inside extension function
//to use in Java
//import static ExtensionFunction.kt.lastChar;
//char c = lastChar("string")

fun String.repeat(n: Int): String {
    val sb = StringBuilder(n * length)
    for (i in 1..n) {
        sb.append(this)
    }
    return sb.toString()
}
//to use it ine Java
//ExtensionFunction.kt.repeat("string", n)

fun String.get(index: Int) = "*"

fun main() {
    val s = "12345"
    println(s.lastChar())
    println("abcde".get(2))
}