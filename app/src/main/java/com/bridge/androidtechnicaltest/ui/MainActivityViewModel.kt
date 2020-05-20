package com.bridge.androidtechnicaltest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bridge.androidtechnicaltest.db.Pupil
import com.bridge.androidtechnicaltest.ui.extensions.LiveDataEvent

class MainActivityViewModel : ViewModel() {
    private val contentLiveData = MutableLiveData<LiveDataEvent<Navigation>>()

    sealed class Navigation {
        data class ToPupilDetails(val pupil: Pupil) : Navigation()
    }

    fun getContentObservable(): LiveData<LiveDataEvent<Navigation>> = contentLiveData
    fun goto(details: Navigation.ToPupilDetails) {
        contentLiveData.value = LiveDataEvent(details)
    }
}