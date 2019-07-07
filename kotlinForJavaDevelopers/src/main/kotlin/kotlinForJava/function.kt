@file:JvmName("Func") // change name of the compiled class file
package kotlinForJava

//fun max(a: Int, b: Int): Int {
//    return if (a > b) a else b
//}

//fun max(a: Int, b: Int): Int = if (a > b) a else b

fun max(a: Int, b: Int) = if (a > b) a else b

fun printMax(a: Int, b: Int): Unit {
    println(max(a, b))
}

class A {
    fun foo() {
        fun localFoo() {

        }
    }
}

fun main() {
    printMax(13, 14)
}