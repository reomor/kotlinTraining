package kotlinForJava

fun main() {
    //while (condition) {}
    //do {} while (condition)
    var list = listOf("a", "b", "c")
    for (item in list) {
        println(item)
    }
    val map = mapOf(1 to "1", 2 to "2")
    for ((key, value) in map) {
        println("$key: $value")
    }
    for( (index, value) in list.withIndex()) {
        println("$index: $value")
    }
    for (i in 1 .. 9) {
        print(i)
    }
    println()
    for (i in 1 until 9) {
        print(i)
    }
    println()
    for (i in 9 downTo 1 step 2) {
        print(i)
    }
    println()
    for (ch in '0' until '9') {
        print(ch + 1)
    }
}