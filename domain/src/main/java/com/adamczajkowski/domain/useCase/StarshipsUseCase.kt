package com.adamczajkowski.domain.useCase

import com.adamczajkowski.common.models.SimpleResponse
import com.adamczajkowski.domain.repository.IStarshipsRepository
import io.reactivex.rxjava3.core.Observable

class StarshipsUseCase(private val starshipRepository: IStarshipsRepository) {
    fun getStarships(page: Int): Observable<SimpleResponse> {
        return starshipRepository.getStarships(page)
    }
}