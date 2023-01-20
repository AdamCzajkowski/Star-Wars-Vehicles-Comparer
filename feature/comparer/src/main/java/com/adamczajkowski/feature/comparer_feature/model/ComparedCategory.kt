package com.adamczajkowski.feature.comparer_feature.model

data class ComparedCategory(
    val lowestValue: String?,
    val highestValue: String?
)

data class CategoriesCombine(
    val costInCredit: ComparedCategory,
    val length: ComparedCategory,
    val maxAtmospheringSpeed: ComparedCategory,
    val crew: ComparedCategory,
    val passengers: ComparedCategory,
    val cargoCapacity: ComparedCategory,
    val consumables: ComparedCategory,
    val hyperdriveRating: ComparedCategory,
    val mglt: ComparedCategory,
)