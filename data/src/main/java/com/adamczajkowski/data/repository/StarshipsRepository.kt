package com.adamczajkowski.data.repository

import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.common.utils.applySchedulers
import com.adamczajkowski.data.model.toListOfStarships
import com.adamczajkowski.data.service.IStarshipsService
import com.adamczajkowski.domain.repository.IStarshipsRepository
import io.reactivex.rxjava3.core.Observable

class StarshipsRepository(
    private val starShipsService: IStarshipsService
) : IStarshipsRepository {

    override fun getStarships(page: Int): Observable<List<Starship>> {
        return starShipsService.getStarships(page)
            .map {
                it.results.toListOfStarships()
            }.applySchedulers()
    }
}