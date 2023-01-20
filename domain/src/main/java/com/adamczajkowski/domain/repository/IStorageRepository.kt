package com.adamczajkowski.domain.repository

import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.common.models.StarshipComparisonHistoryItem
import io.reactivex.rxjava3.core.Observable

interface IStorageRepository {
    fun getLastTenComparison(): Observable<List<StarshipComparisonHistoryItem>>
    fun saveComparison(listOfStarships : List<Starship>)
}