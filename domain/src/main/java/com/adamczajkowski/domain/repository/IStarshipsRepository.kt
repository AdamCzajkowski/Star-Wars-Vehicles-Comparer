package com.adamczajkowski.domain.repository

import com.adamczajkowski.common.models.SimpleResponse
import io.reactivex.rxjava3.core.Observable

interface IStarshipsRepository {
    fun getStarships(page: Int): Observable<SimpleResponse>
}