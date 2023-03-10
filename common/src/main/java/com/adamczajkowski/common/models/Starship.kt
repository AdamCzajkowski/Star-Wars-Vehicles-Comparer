package com.adamczajkowski.common.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Starship(
    val name: String?,
    val model: String?,
    val manufacturer: String?,
    val costInCredits: String?,
    val length: String?,
    val maxAtmospheringSpeed: String?,
    val crew: String?,
    val passengers: String?,
    val cargoCapacity: String?,
    val consumables: String?,
    val hyperdriveRating: String?,
    val mglt: String?,
    val starshipClass: String?,
    var isSelected: Boolean = false
): Parcelable