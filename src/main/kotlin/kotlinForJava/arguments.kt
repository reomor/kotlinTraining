package kotlinForJava

@JvmOverloads // generate 4 overloaded function for Java
// sum()
// sum(int a)
// sum(int a, int b)
// sum(int a, int b, int c)
fun sum(a: Int = 0, b: Int = 0, c: Int = 0) = a + b + c

fun main() {
    // named argument
    println(listOf('a', 'b', 'c').joinToString(
        prefix = "["
    ))
    // default values
    fun printSeparator(character: Char = '*', amount: Int = 10) {
        repeat(amount) {
            print(character)
        }
        println()
    }

    printSeparator()
    printSeparator('#')
    printSeparator(character = '%', amount = 5)

    sum(0, 1, c = 1)
}