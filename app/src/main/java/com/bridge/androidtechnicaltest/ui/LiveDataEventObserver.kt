package com.bridge.androidtechnicaltest.ui

import androidx.lifecycle.Observer

class LiveDataEventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<LiveDataEvent<T>> {
    override fun onChanged(event: LiveDataEvent<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}