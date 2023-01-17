package com.adamczajkowski.data.service

import com.adamczajkowski.data.api.StarWarsAPI
import com.adamczajkowski.data.model.Response
import io.reactivex.rxjava3.core.Observable

interface IStarshipsService {
    fun getStarships(
        page: Int
    ): Observable<Response>
}

class StarshipsService(private val starWarsAPI: StarWarsAPI) : IStarshipsService {
    override fun getStarships(page: Int): Observable<Response> {
        return starWarsAPI.getStarships(page)
    }
}