@file:Suppress("UNCHECKED_CAST")

package com.adamczajkowski.feature.comparer_feature.utils

import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.feature.comparer_feature.model.Category
import com.adamczajkowski.feature.comparer_feature.model.ComparedCategory
import kotlin.math.roundToLong

object CompareHelper {

    private const val UNKNOWN_EXCEPTION = "unknown"
    private const val N_A_EXCEPTION = "n/a"

    // To simplify the calculation, when a value is in the range with "-", the lowest value is considered
    private const val DASH_DELIMITER = "-"
    private const val COMMA = ","

    private const val ONLY_DIGITS_REGEX = "[^\\d.]"

    private const val HOURS_IN_HOUR = 1
    private const val HOURS_IN_DAY = 24
    private const val HOURS_IN_WEEK = 168

    // To simplify calculation 1 month = 31 days
    private const val HOURS_IN_MONTH = 744

    // To simplify calculation 1 year = 365 days
    private const val HOURS_IN_YEAR = 8760

    private const val HOUR = "hour"
    private const val DAY = "day"
    private const val WEEK = "week"
    private const val MONTH = "month"
    private const val YEAR = "year"

    fun compareNumbers(starships: List<Starship>, category: Category): ComparedCategory {
        val data = getCategoriesValuesList(starships, category)
            .filterNotUnknownOrNaValues()
            .removeUselessDataAndParseToLongArray(category)

        var lowestValue: String? = null
        var highestValue: String? = null

        if (data.isNotEmpty()) {

            var min: Pair<String, Long>? = null
            for (vehicle in data) {
                if (min == null || min.second > vehicle.second) {
                    min = vehicle
                }
            }
            lowestValue = min?.first

            var max: Pair<String, Long>? = null
            for (vehicle in data) {
                if (max == null || max.second < vehicle.second) {
                    max = vehicle
                }
            }
            highestValue = max?.first
        }

        return ComparedCategory(lowestValue, highestValue)
    }

    private fun List<String>.removeUselessDataAndParseToLongArray(category: Category): List<Pair<String, Long>> {
        return when (category) {
            Category.COST_IN_CREDITS, Category.PASSENGERS, Category.CARGO_CAPACITY, Category.MGLT, Category.MAX_ATMOSPHERING_SPEED ->
                map { it.removeLetters() }
                    .map { it.toSecondLong() }
            Category.LENGTH, Category.CREW, Category.HYPERDRIVE_RATING ->
                map { it.removeCommas() }
                    .map { it.toSecondTakeLowestBeforeDashDelimiter() }
                    .map { it.toSecondDouble() }
                    .map { it.toSecondLongWithRounded() }
            Category.CONSUMABLES -> map { it.calculateConsumablesTime() }
        }
    }

    private fun getCategoriesValuesList(
        starships: List<Starship>,
        category: Category
    ): List<String> {
        return when (category) {
            Category.COST_IN_CREDITS -> starships.mapNotNull { it.costInCredits }
            Category.PASSENGERS -> starships.mapNotNull { it.passengers }
            Category.CARGO_CAPACITY -> starships.mapNotNull { it.cargoCapacity }
            Category.MGLT -> starships.mapNotNull { it.mglt }
            Category.MAX_ATMOSPHERING_SPEED -> starships.mapNotNull { it.maxAtmospheringSpeed }
            Category.LENGTH -> starships.mapNotNull { it.length }
            Category.CREW -> starships.mapNotNull { it.crew }
            Category.HYPERDRIVE_RATING -> starships.mapNotNull { it.hyperdriveRating }
            Category.CONSUMABLES -> starships.mapNotNull { it.consumables }
        }
    }

    private fun String.calculateConsumablesTime(): Pair<String, Long> {
        return when {
            this.contains(HOUR) ->
                Pair(this, this.replace(ONLY_DIGITS_REGEX.toRegex(), "").toLong() * HOURS_IN_HOUR)
            this.contains(DAY) ->
                Pair(this, this.replace(ONLY_DIGITS_REGEX.toRegex(), "").toLong() * HOURS_IN_DAY)
            this.contains(WEEK) ->
                Pair(this, this.replace(ONLY_DIGITS_REGEX.toRegex(), "").toLong() * HOURS_IN_WEEK)
            this.contains(MONTH) ->
                Pair(this, this.replace(ONLY_DIGITS_REGEX.toRegex(), "").toLong() * HOURS_IN_MONTH)
            this.contains(YEAR) ->
                Pair(this, this.replace(ONLY_DIGITS_REGEX.toRegex(), "").toLong() * HOURS_IN_YEAR)
            else -> Pair(this, this.toLong())
        }
    }

    private fun String.removeLetters(): Pair<String, String> {
        return Pair(this, this.replace(ONLY_DIGITS_REGEX.toRegex(), ""))
    }

    private fun List<String>.filterNotUnknownOrNaValues(): List<String> {
        return filter { it != UNKNOWN_EXCEPTION }.filter { it != N_A_EXCEPTION }
    }

    private fun String.removeCommas(): Pair<String, String> {
        return Pair(this, replace(COMMA, ""))
    }

    private fun Pair<String, Double>.toSecondLongWithRounded(): Pair<String, Long> {
        return Pair(first, second.roundToLong())
    }

    private fun Pair<String, String>.toSecondLong(): Pair<String, Long> {
        return Pair(first, second.toLong())
    }

    private fun Pair<String, String>.toSecondTakeLowestBeforeDashDelimiter(): Pair<String, String> {
        return Pair(first, second.substringBefore(DASH_DELIMITER))
    }

    private fun Pair<String, String>.toSecondDouble(): Pair<String, Double> {
        return Pair(first, second.toDouble())
    }
}