package com.adamczajkowski.common.models

data class SimpleResponse(
    val isNext: Boolean,
    val results: List<Starship>
)