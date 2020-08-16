package kotlinForJava.hw.taxipark

import kotlin.math.roundToInt

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> {
    return allDrivers.minus(trips.map { it.driver })
}

/*
 allDrivers.filter { driver -> trips.none { it.driver == driver } } .toSet()
 */

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
    if (minTrips == 0) {
        allPassengers.toSet()
    } else {
        trips.flatMap(Trip::passengers)
            .groupBy { passenger -> passenger }
            .filterValues { group -> group.size >= minTrips }
            .keys
    }

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
    trips.filter { it.driver == driver }
        .flatMap(Trip::passengers)
        .groupBy { passenger -> passenger }
        .filterValues { group -> group.size > 1 }
        .keys
/*
allPassengers.filter { passenger -> trips.count { it.driver == driver && passenger in it.passengers } > 1 }.toSet()
 */

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
    allPassengers.associateWith { passenger -> trips.filter { trip -> passenger in trip.passengers } }
        .filterValues { group ->
            val (withDiscount, withoutDiscount) = group.partition { it.discount != null }
            withDiscount.size > withoutDiscount.size
        }.keys

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return if (trips.isEmpty()) {
        null
    } else {
        trips
            .groupBy {
                val rangeStart = it.duration / 10 * 10
                IntRange(rangeStart, rangeStart + 9)
            }
            .maxBy { (_, group) -> group.size }
            ?.key
    }
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (trips.isEmpty()) return false
    val totalCost = trips.sumByDouble(Trip::cost )
    val driverCost = trips.asSequence()
        .map {trip -> trip.driver to trip.cost }
        .groupBy({ it.first }, { it.second })
        .map { it.key to it.value.sum() }
        .toList()
        .sortedByDescending { it.second }
        .take((allDrivers.size * 0.2).roundToInt())
        .sumByDouble { it.second }
    return driverCost >= totalCost * 0.8
}
