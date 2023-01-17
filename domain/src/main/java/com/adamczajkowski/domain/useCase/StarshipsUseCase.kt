package com.adamczajkowski.domain.useCase

import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.domain.repository.IStarshipsRepository
import io.reactivex.rxjava3.core.Observable

class StarshipsUseCase(private val starshipRepository: IStarshipsRepository) {
    fun getStarships(page: Int): Observable<List<Starship>> {
        return starshipRepository.getStarships(page)
    }
}