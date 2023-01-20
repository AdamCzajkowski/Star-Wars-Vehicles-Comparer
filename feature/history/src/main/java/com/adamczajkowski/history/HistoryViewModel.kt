package com.adamczajkowski.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adamczajkowski.common.di.SCHEDULER_IO
import com.adamczajkowski.common.di.SCHEDULER_MAIN_THREAD
import com.adamczajkowski.common.models.StarshipComparisonHistoryItem
import com.adamczajkowski.domain.useCase.HistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HistoryViewModel @Inject constructor(
    @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler,
    private val historyUseCase: HistoryUseCase
) : ViewModel() {

    private val _comparison: MutableLiveData<List<StarshipComparisonHistoryItem>> =
        MutableLiveData()
    val comparison: LiveData<List<StarshipComparisonHistoryItem>> get() = _comparison

    private val _errorMessage: MutableLiveData<String?> = MutableLiveData()
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val disposable = CompositeDisposable()

    fun getLastComparison() {
        disposable.add(
            historyUseCase.getLastTenComparedVehicles()
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe(
                    ::onLastComparisonSuccessFetched,
                    ::onLastComparisonFailureFetched
                )
        )
    }

    private fun onLastComparisonSuccessFetched(list: List<StarshipComparisonHistoryItem>) {
        _comparison.postValue(list)
    }

    private fun onLastComparisonFailureFetched(error: Throwable) {
        _errorMessage.postValue(error.message)
    }
}