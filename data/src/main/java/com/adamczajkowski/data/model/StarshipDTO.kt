package com.adamczajkowski.data.model

import android.os.Parcelable
import com.adamczajkowski.common.models.Starship
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class StarshipDTO(
    @SerializedName("name") val name: String?,
    @SerializedName("model") val model: String?,
    @SerializedName("manufacturer") val manufacturer: String?,
    @SerializedName("cost_in_credits") val costInCredits: String?,
    @SerializedName("length") val length: String?,
    @SerializedName("max_atmosphering_speed") val maxAtmospheringSpeed: String?,
    @SerializedName("crew") val crew: String?,
    @SerializedName("passengers") val passengers: String?,
    @SerializedName("cargo_capacity") val cargoCapacity: String?,
    @SerializedName("consumables") val consumables: String?,
    @SerializedName("hyperdrive_rating") val hyperdriveRating: String?,
    @SerializedName("MGLT") val mglt: String?,
    @SerializedName("starship_class") val starshipClass: String?
) : Parcelable

fun StarshipDTO.toStarship() = Starship(
    name,
    model,
    manufacturer,
    costInCredits,
    length,
    maxAtmospheringSpeed,
    crew,
    passengers,
    cargoCapacity,
    consumables,
    hyperdriveRating,
    mglt,
    starshipClass
)

fun List<StarshipDTO>.toListOfStarships() = map { it.toStarship() }
