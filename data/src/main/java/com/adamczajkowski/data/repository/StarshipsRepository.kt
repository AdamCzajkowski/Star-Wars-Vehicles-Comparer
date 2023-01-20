package com.adamczajkowski.data.repository

import com.adamczajkowski.common.models.SimpleResponse
import com.adamczajkowski.common.utils.applySchedulers
import com.adamczajkowski.data.model.toSimpleResponse
import com.adamczajkowski.data.service.IStarshipsService
import com.adamczajkowski.domain.repository.IStarshipsRepository
import io.reactivex.rxjava3.core.Observable

class StarshipsRepository(
    private val starShipsService: IStarshipsService
) : IStarshipsRepository {

    override fun getStarships(page: Int): Observable<SimpleResponse> {
        return starShipsService.getStarships(page)
            .map {
                it.toSimpleResponse()
            }.applySchedulers()
    }
}