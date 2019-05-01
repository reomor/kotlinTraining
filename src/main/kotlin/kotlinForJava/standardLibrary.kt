package kotlinForJava


fun sumOriginal(list: List<Int>): Int {
    var result = 0
    for (i in list) {
        result += i
    }
    return result
}
/*
fun main(args: Array<String>) {
    val sum = sum(listOf(1, 2, 3))
    println(sum)    // 6
}
 */

fun List<Int>.sum(): Int {
    var result = 0
    for (i in this) {
        result += i
    }
    return result
}

fun main(args: Array<String>) {
    val sum1 = sumOriginal(listOf(4, 5, 6))
    val sum = listOf(1, 2, 3).sum()
    println(sum)    // 6
}
