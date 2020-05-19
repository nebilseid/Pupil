package com.bridge.androidtechnicaltest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bridge.androidtechnicaltest.db.Pupil
import com.bridge.androidtechnicaltest.db.PupilList
import com.bridge.androidtechnicaltest.db.PupilRepository
import io.reactivex.disposables.CompositeDisposable

class PupilViewModel constructor(private val repository: PupilRepository) : ViewModel() {

    private val disposable = CompositeDisposable()
    val pupilViewModel: MutableLiveData<PupilList> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun getPupils() {
        disposable.add(
                repository.pupilApi.getPupils()
                        .subscribe({
                            if (it.items.isEmpty()) {
                                error.value = "No data found"
                            } else {
                                pupilViewModel.value = it
                            }
                        }, {
                            it.printStackTrace()
                        })
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }


}