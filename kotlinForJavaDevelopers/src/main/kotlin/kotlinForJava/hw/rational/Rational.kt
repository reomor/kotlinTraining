package kotlinForJava.hw.rational

import java.lang.IllegalArgumentException
import java.math.BigInteger

/*
@Suppress("DataClassPrivateConstructor")
data class RationalData
private constructor(val n: BigInteger, val d: BigInteger) : Comparable<RationalData> {
    override fun compareTo(other: RationalData): Int {
        val left = this.n * other.d
        val right = other.n * this.d
        return left.minus(right).signum()
    }

    companion object {
        fun create(n: BigInteger, d: BigInteger): RationalData  {
            TODO()}
        fun normalize(n: BigInteger, d: BigInteger): RationalData {
                    val signum = denominator.signum().toBigInteger()
        val gcd = numerator.gcd(denominator)
        return RationalData(
            numerator / gcd * signum,
            denominator / gcd * signum
        )
        }
    }
}
*/

class Rational(n: BigInteger, d: BigInteger) : Comparable<Rational> {

    val numerator: BigInteger
    val denominator: BigInteger

    init {
        val signum = d.signum().toBigInteger()
        val gcd = n.gcd(d)
        numerator = n / gcd * signum
        denominator = d / gcd * signum
    }

    override fun compareTo(other: Rational): Int {
        return (numerator * other.denominator).minus(other.numerator * denominator).signum()
    }

    override fun toString(): String {
        if (denominator == BigInteger.ONE) {
            return "$numerator"
        }
        return "$numerator/$denominator"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rational

        if (numerator != other.numerator) return false
        if (denominator != other.denominator) return false

        return true
    }

    override fun hashCode(): Int {
        var result = numerator.hashCode()
        result = 31 * result + denominator.hashCode()
        return result
    }
}

class RationalRange<Rational : Comparable<Rational>>(
    override val start: Rational,
    override val endInclusive: Rational
) : ClosedRange<Rational>

operator fun Rational.plus(other: Rational) =
    Rational(
        this.numerator * other.denominator + other.numerator * this.denominator,
        this.denominator * other.denominator
    )

operator fun Rational.minus(other: Rational) =
    Rational(
        this.numerator * other.denominator - other.numerator * this.denominator,
        this.denominator * other.denominator
    )

operator fun Rational.times(other: Rational) =
    Rational(
        this.numerator * other.numerator,
        this.denominator * other.denominator
    )

operator fun Rational.div(other: Rational) =
    Rational(this.numerator * other.denominator, this.denominator * other.numerator)

operator fun Rational.unaryMinus() =
    Rational(-this.numerator, this.denominator)

operator fun Rational.rangeTo(end: Rational) = RationalRange(this, end)

fun String.toRational(): Rational {
    fun String.toBigIntegerOrFail() = toBigIntegerOrNull() ?: throw IllegalArgumentException(
        "Expecting rational in the form of 'n/d' or 'n', but get: '${this@toRational}'"
    )
    if (!contains("/")) {
        return Rational(toBigIntegerOrFail(), BigInteger.ONE)
    }
    val (numer, denum) = split("/")
    return Rational(numer.toBigIntegerOrFail(), denum.toBigIntegerOrFail())
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