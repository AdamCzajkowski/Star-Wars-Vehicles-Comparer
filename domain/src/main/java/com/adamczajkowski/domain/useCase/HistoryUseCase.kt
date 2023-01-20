package com.adamczajkowski.domain.useCase

import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.common.models.StarshipComparisonHistoryItem
import com.adamczajkowski.domain.repository.IStorageRepository
import io.reactivex.rxjava3.core.Observable

class HistoryUseCase(
    private val storageRepository: IStorageRepository
) {
    fun getLastTenComparedVehicles(): Observable<List<StarshipComparisonHistoryItem>> {
        return storageRepository.getLastTenComparison()
    }

    fun saveComparison(list: List<Starship>) {
        return storageRepository.saveComparison(list)
    }
}