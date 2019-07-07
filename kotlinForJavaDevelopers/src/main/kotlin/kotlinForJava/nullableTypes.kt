package kotlinForJava

fun main() {
    val s1: String = "not null" //can't be null
    val s2: String? = null //can be null

    val result1: String = s2 ?: ""
    val result2: String? = s2

    val length1: Int? = s2?.length
    val length2: Int = s2?.length ?: 0

    length1!! //flow control after that length1 is not allowed

    val length11: Int = length1

    fun foo(list1: List<Int?>, list2: List<Int>?) {
        list1.size
        list2?.size

        val i: Int? = list1[0]
        val j: Int? = list2?.get(0)
    }
}