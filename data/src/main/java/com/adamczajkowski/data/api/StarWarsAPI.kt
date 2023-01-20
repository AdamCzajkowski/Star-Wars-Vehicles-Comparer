package com.adamczajkowski.data.api

import com.adamczajkowski.data.model.Response
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsAPI {

    @GET("api/starships")
    fun getStarships(
        @Query("page") page: Int? = null
    ): Observable<Response>
}