package com.adamczajkowski.feature.comparer_feature.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adamczajkowski.common.di.SCHEDULER_IO
import com.adamczajkowski.common.di.SCHEDULER_MAIN_THREAD
import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.domain.useCase.HistoryUseCase
import com.adamczajkowski.domain.useCase.StarshipsUseCase
import com.adamczajkowski.feature.comparer_feature.model.CategoriesCombine
import com.adamczajkowski.feature.comparer_feature.model.Category
import com.adamczajkowski.feature.comparer_feature.utils.CompareHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class TableViewModel @Inject constructor(
    @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler,
    private val starshipsUseCase: StarshipsUseCase,
    private val historyUseCase: HistoryUseCase
) : ViewModel() {

    private val _starships: MutableLiveData<List<Starship>> = MutableLiveData()
    val starships: LiveData<List<Starship>> get() = _starships

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage: MutableLiveData<String?> = MutableLiveData()
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val disposable = CompositeDisposable()

    private var currentPage = 1

    fun fetchStarships(isFirstPage: Boolean) {
        updateProgressBarVisibility(true)

        if (isFirstPage) currentPage = 1
        disposable.add(
            starshipsUseCase.getStarships(currentPage)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe(
                    { response ->
                        _starships.postValue(
                            if (isFirstPage) response.results
                            else _starships.value?.plus(response.results)
                        )
                        if (response.isNext) {
                            currentPage++
                            fetchStarships(false)
                        } else {
                            updateProgressBarVisibility(false)
                        }
                    }, ::onFetchStarshipsFailure
                )
        )
    }

    fun updateListOfStarships(updatedStarship: Starship) {
        _starships.value.apply {
            this?.find { it.name == updatedStarship.name }?.isSelected = updatedStarship.isSelected
            this?.let { _starships.postValue(it) }
        }
    }

    fun saveComparison() {
        historyUseCase.saveComparison(getSelectedVehicles())
    }

    fun getSelectedCount(): Int {
        return _starships.value?.filter { it.isSelected }?.size ?: 0
    }

    fun getSelectedVehicles(): List<Starship> {
        return _starships.value?.filter { it.isSelected } ?: listOf()
    }

    fun getComparedCategories(): CategoriesCombine {
        val starships = getSelectedVehicles()
        return CategoriesCombine(
            costInCredit = CompareHelper.compareNumbers(starships, Category.COST_IN_CREDITS),
            length = CompareHelper.compareNumbers(starships, Category.LENGTH),
            maxAtmospheringSpeed = CompareHelper.compareNumbers(
                starships,
                Category.MAX_ATMOSPHERING_SPEED
            ),
            crew = CompareHelper.compareNumbers(starships, Category.CREW),
            passengers = CompareHelper.compareNumbers(starships, Category.PASSENGERS),
            cargoCapacity = CompareHelper.compareNumbers(starships, Category.CARGO_CAPACITY),
            consumables = CompareHelper.compareNumbers(starships, Category.CONSUMABLES),
            hyperdriveRating = CompareHelper.compareNumbers(starships, Category.HYPERDRIVE_RATING),
            mglt = CompareHelper.compareNumbers(starships, Category.MGLT)
        )
    }

    private fun updateProgressBarVisibility(isEnable: Boolean) {
        _isLoading.postValue(isEnable)
    }

    private fun onFetchStarshipsFailure(error: Throwable) {
        _errorMessage.postValue(error.message)
    }

    fun onDestroyView() {
        disposable.dispose()
    }
}