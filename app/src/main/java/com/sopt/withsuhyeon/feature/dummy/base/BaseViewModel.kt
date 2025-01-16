package com.sopt.withsuhyeon.feature.dummy.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : UiState, SideEffect : UiSideEffect, Event : UiEvent> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SideEffect>()
    val sideEffect: SharedFlow<SideEffect> = _sideEffect.asSharedFlow()

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> = _event.asSharedFlow()

    fun setState(reduce: State.() -> State) {
        _uiState.value = _uiState.value.reduce()
    }

    fun setSideEffect(builder: () -> SideEffect) {
        viewModelScope.launch { _sideEffect.emit(builder()) }
    }

    open fun setEvent(event: Event) {
        dispatchEvent(event)
    }

    private fun dispatchEvent(event: Event) = viewModelScope.launch {
        handleEvent(event)
    }

    protected abstract suspend fun handleEvent(event: Event)
}