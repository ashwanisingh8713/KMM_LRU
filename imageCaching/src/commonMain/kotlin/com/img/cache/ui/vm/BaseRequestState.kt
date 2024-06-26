package com.img.cache.ui.vm

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

abstract class BaseRequestState<State>: KoinComponent {

    protected abstract fun createInitialState():State
    private val initialState: State by lazy { createInitialState() }

    protected val currentState: State
        get() = resultState.value

    private val _resultState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val resultState = _resultState.asStateFlow()

    fun setState(reduce:State.()->State) {
       val newState = currentState.reduce()
        _resultState.value = newState
    }

}