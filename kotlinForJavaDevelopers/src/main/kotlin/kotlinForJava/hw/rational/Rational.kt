package kotlinForJava.hw.rational

import java.lang.IllegalArgumentException
import java.math.BigInteger

data class Rational(val numerator: BigInteger, val denominator: BigInteger) : Comparable<Rational> {

    override fun compareTo(other: Rational): Int {
        val left = this.numerator * other.denominator
        val right = other.numerator * this.denominator
        return left.minus(right).signum()
    }

    override fun toString(): String {
        if (denominator == BigInteger.ONE) {
            return "$numerator"
        }
        return "$numerator/$denominator"
    }
}

class RationalRange<Rational : Comparable<Rational>>(
    override val start: Rational,
    override val endInclusive: Rational
) : ClosedRange<Rational>

operator fun Rational.plus(other: Rational) =
    (this.numerator * other.denominator + other.numerator * this.denominator) divBy
        (this.denominator * other.denominator)

operator fun Rational.minus(other: Rational) =
    (this.numerator * other.denominator - other.numerator * this.denominator) divBy
        (this.denominator * other.denominator)

operator fun Rational.times(other: Rational) =
    (this.numerator * other.numerator) divBy
        (this.denominator * other.denominator)

operator fun Rational.div(other: Rational) =
    (this.numerator * other.denominator) divBy (this.denominator * other.numerator)

operator fun Rational.unaryMinus() =
    (-this.numerator) divBy (this.denominator)

operator fun Rational.rangeTo(end: Rational) = RationalRange(this, end)

fun String.toRational(): Rational {
    val split = this.split("/").map { BigInteger(it) }
    return when (split.size) {
        1 -> {
            split[0] divBy BigInteger.ONE
        }
        2 -> {
            split[0] divBy split[1]
        }
        else -> throw IllegalArgumentException("Number of params not equals 2")
    }
}

infix fun Int.divBy(other: Int): Rational = BigInteger(this.toString()) divBy BigInteger(other.toString())

infix fun Long.divBy(other: Long): Rational = BigInteger(this.toString()) divBy BigInteger(other.toString())

infix fun BigInteger.divBy(other: BigInteger): Rational {
    val signum = this.signum() * other.signum()
    val gcd = this.gcd(other)
    return Rational(
        this.divide(gcd).abs().multiply(BigInteger(signum.toString())),
        other.divide(gcd).abs()
    )
}

fun main() {

    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    val rationalRange = third..twoThirds
    println(half in rationalRange)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
        "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}