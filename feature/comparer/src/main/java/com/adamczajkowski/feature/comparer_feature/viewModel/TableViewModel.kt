package com.adamczajkowski.feature.comparer_feature.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adamczajkowski.common.di.SCHEDULER_IO
import com.adamczajkowski.common.di.SCHEDULER_MAIN_THREAD
import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.common.utils.Paginator
import com.adamczajkowski.domain.useCase.StarshipsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class TableViewModel @Inject constructor(
    @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler,
    private val starshipsUseCase: StarshipsUseCase
) : ViewModel() {

    private val _starships: MutableLiveData<List<Starship>> = MutableLiveData()
    val starships: LiveData<List<Starship>> get() = _starships

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val paginator: Paginator = Paginator()

    private val disposable = CompositeDisposable()

    fun fetchStarships(isFirstPage: Boolean) {
        if (!isFirstPage && !paginator.canLoadNextPage()) return
        if (isFirstPage) paginator.resetPage()
        updateProgressBarVisibility(true)

        disposable.add(
            starshipsUseCase.getStarships(paginator.currentPage)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .doOnTerminate {
                    updateProgressBarVisibility(false)
                }
                .subscribe(
                    { result ->
                        _starships.postValue(
                            if (isFirstPage) result
                            else _starships.value?.plus(result)
                        )
                        paginator.advanceForLimit(result.size)
                    }, ::onFetchStarshipsFailure
                )
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