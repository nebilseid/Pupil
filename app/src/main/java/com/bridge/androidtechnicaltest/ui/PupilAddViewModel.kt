package com.bridge.androidtechnicaltest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bridge.androidtechnicaltest.db.IPupilRepository
import com.bridge.androidtechnicaltest.db.Pupil
import com.bridge.androidtechnicaltest.db.PupilList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PupilAddViewModel(private val repository: IPupilRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val loadingLiveData = MutableLiveData<Boolean>()
    val pupilContentLiveData = MutableLiveData<Pupil>()
    private val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun addPupil(pupil: Pupil) {
        disposable.add(
                repository.postPupils(pupil)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { loadingLiveData.value = true }
                        .doOnEvent { _, _ -> loadingLiveData.value = false }
                        .subscribe({
                            pupilContentLiveData.value = it
                        }, {
                            it.printStackTrace()
                            errorLiveData.value = it.message
                        })
        )
    }


    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    fun getLoadingObservable(): LiveData<Boolean> = loadingLiveData
    fun getPupilsContentObservable(): MutableLiveData<Pupil> = pupilContentLiveData
    fun getErrorObservable(): LiveData<String> = errorLiveData
}