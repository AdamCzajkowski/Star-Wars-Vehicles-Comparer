package com.adamczajkowski.data.repository

import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.common.models.StarshipComparisonHistoryItem
import com.adamczajkowski.common.utils.applySchedulers
import com.adamczajkowski.data.database.dao.StarshipDao
import com.adamczajkowski.data.database.entity.StarshipEntity
import com.adamczajkowski.data.database.entity.toStarshipComparisonHistoryItem
import com.adamczajkowski.domain.repository.IStorageRepository
import io.reactivex.rxjava3.core.Observable
import java.util.*

class StorageRepository(
    private val starshipDao: StarshipDao
): IStorageRepository {

    override fun getLastTenComparison(): Observable<List<StarshipComparisonHistoryItem>> {
        return Observable.defer {
            Observable.just(starshipDao.getLastTenDescending())
                .map { it.map { entity -> entity.toStarshipComparisonHistoryItem() } }
        }.applySchedulers()
    }

    override fun saveComparison(listOfStarships: List<Starship>) {
        val starshipEntity = StarshipEntity(
            listOfStarships.map { it.name }.toString(),
            Date()
        )
        Observable.defer {
            starshipDao.insert(starshipEntity)

            val size = starshipDao.getRowCount()
            if(size > MAX_LAST_COMPARED_SIZE) {
                val entityToRemove = starshipDao.getLastlyAddedComparison()
                starshipDao.delete(entityToRemove)
            }
            Observable.empty<Any>()
        }.applySchedulers().publish().connect()
    }

    companion object {
        private const val MAX_LAST_COMPARED_SIZE = 10
    }
}