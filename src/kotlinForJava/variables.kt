package kotlinForJava

fun main(args: Array<String>): Unit {
    //var mutable: String = 0
    var mutable = 0
    mutable = 42
    println(mutable)

    val immutable = 42
    //immutable = 2 error
    println(immutable)

    //val readOnlyList = listOf<String>("Java")
    val readOnlyList = listOf("Java")
    val mutableList = mutableListOf("Java")
    mutableList.add("Kotlin")
}