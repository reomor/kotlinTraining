package kotlinForJava

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z' //in
fun isNotDigit(c: Char) = c !in '0'..'9' //not in
fun regonize(c: Char) = when(c) {
    in '0' .. '9' -> "it's a digit"
    in 'a'..'z', in 'A'..'Z' -> "it's a letter"
    else -> "not regonizable"
}

class MyDate : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return this.compareTo(other)
    }
}

// hw
// https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
fun isValidIdentifier(s: String): Boolean {
    return s.matches("^[a-zA-Z_][\\w_]*".toRegex())
}

fun main(args: Array<String>) {
//    for (i in 'a' .. 'z') {}
//    val ch: Char = '0'
//    if(ch in 'a' .. 'z') {}

    //different ranges for comparable objects
//    1..9 IntRange
//    1 until 10 IntRange
//    'a'..'z'CharRange
//    "ab" .. "az" ClosedRange<String>
//    startDate .. endDate ClosedRange<MyData>

    val res = "ball" in "a" .. "k"
    println(res)
    val myDate: MyDate = MyDate()
    val startDate = MyDate()
    val endDate = MyDate()
    if (myDate.compareTo(startDate) >= 0 && myDate.compareTo(endDate) <= 0) {}
    if (myDate >= startDate && myDate <= endDate) {}
    if (myDate in startDate .. endDate) {}

    //check belonging to list
    //if (element in list) {}
    //the same as
    // if(list.contains(element)) {}
}