package kotlinForJava

import kotlinForJava.Gender.*

enum class Gender { MALE, FEMALE }

data class Hero (
    val name: String,
    val age: Int,
    val gender: Gender?
)

fun main() {
    val function = { x: Int, y: Int -> x + y }
    println(function(1,2))

    val heroes = listOf(
        Hero("The Captain", 60, MALE),
        Hero("Frenchy", 42, MALE),
        Hero("The Kid", 9, null),
        Hero("Lady Lauren", 29, FEMALE),
        Hero("First Mate", 29, MALE),
        Hero("Sir Stephen", 37, MALE))

    //heroes.first({ it.age == 30}).name - exception
    println(heroes.firstOrNull({ it.age == 30})?.name)
    println(heroes.map { it.age }.distinct().size)
    println(heroes.filter { it.age < 30 }.size)
    val (younger, older) = heroes.partition { it.age < 30 }
    println(older.size)
    println(heroes.maxBy { it.age }?.name)
    println(heroes.all { it.age < 50 })
    println(heroes.any { it.gender == FEMALE})

    val mapByAge: Map<Int, List<Hero>> = heroes.groupBy { it.age }
    val (age, group) = mapByAge.maxBy { (_, group) -> group.size }!! //NPE if null
    println(age)

    val associateBy: Map<String, Hero> = heroes.associateBy { it.name }
    println(associateBy["Frenchy"]?.age)

    val unknownHero = Hero("unknown", 0, null)
    println(associateBy.getOrElse("unknown") { unknownHero }.age)
    val flatMap = heroes.flatMap { first -> heroes.map { second -> first to second } }
    val (heroFirst, heroSecond) = flatMap.maxBy { it.first.age - it.second.age }!!
    println(heroFirst.name)
}