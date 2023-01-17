package com.adamczajkowski.domain.repository

import com.adamczajkowski.common.models.Starship
import io.reactivex.rxjava3.core.Observable

interface IStarshipsRepository {
    fun getStarships(page: Int): Observable<List<Starship>>
}