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
            trips.flatMap { it.passengers }
                    .groupBy { it }
                    .filter { it.value.size >= minTrips }
                    .map { it.key }
                    .toSet()
        }

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        trips.filter { it.driver == driver }
                .flatMap { it.passengers }
                .groupBy { it }
                .filter { it.value.size > 1 }
                .map { it.key }
                .toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
        trips.flatMap { trip -> trip.passengers.associateWith { trip }.toList() }
                .asSequence()
                .groupBy({ it.first }, { it.second })
                .map { entry -> entry.key to entry.value.partition { it.discount == null } }
                .filter { it.second.first.size < it.second.second.size }
                .map { it.first }
                .toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return if (trips.isEmpty()) {
        null
    } else {
        trips.map { IntRange(it.duration / 10 * 10, it.duration / 10 * 10 + 9) to it }
                .groupBy { it.first }
                .maxBy { it.value.size }
                ?.component1()
    }
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    val totalCost = trips.map { it.cost }.sum()
    return if (trips.isEmpty()) {
        false
    } else {
        val driverCost = trips.map { it.driver to it.cost }
                .groupBy({ it.first }, { it.second })
                .map { it.key to it.value.sum() }
                .toList()
                .sortedByDescending { it.second }
                .take((allDrivers.size * 0.2).roundToInt())
                .sumByDouble { it.second }
        driverCost >= totalCost * 0.8
    }
}
