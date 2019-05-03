package kotlinForJava.hw.extension

//fun String?.isEmptyOrNull(): Boolean {
//    if (this == null || this.isEmpty()) return true
//    return false
//}
fun String?.isEmptyOrNull(): Boolean = this == null || this.isEmpty()

fun main() {
    val s: Double = 1.0
    println(s as? Int)    // null
    println(s as Int?)    // exception
}