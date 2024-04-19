package com.img.app.ui

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

abstract class BaseViewModel<State>: KoinComponent, ScreenModel {

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