package com.adamczajkowski.data.model

import com.adamczajkowski.common.models.SimpleResponse
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<StarshipDTO>
)

fun Response.toSimpleResponse() = SimpleResponse(
    isNext = next != null,
    results = results.toListOfStarships()
)