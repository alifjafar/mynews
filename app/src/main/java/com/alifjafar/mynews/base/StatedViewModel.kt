package com.alifjafar.mynews.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class StatedViewModel<S> : ViewModel() {

    protected abstract fun initState(): S
    val state: S = this.initState()

    private val liveState: MutableLiveData<S> by lazy {
        MutableLiveData<S>().apply {
            value = state
        }
    }

    fun getState(): LiveData<S> = liveState

    fun setState(update: S.() -> Unit) {
        state.update()
        updateState()
    }

    protected fun updateState() {
        liveState.value = state
    }
}