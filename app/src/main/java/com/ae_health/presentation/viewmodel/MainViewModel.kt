package com.ae_health.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.model.util.ScreenDestinations
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
): ViewModel() {

    private val _screenUIState: MutableStateFlow<ScreenUIState> = MutableStateFlow(ScreenUIState())
    val screenUIState = _screenUIState.asStateFlow()

    fun onEvent(event: ScreenUIEvent) = when (event) {
        is ScreenUIEvent.ChangeSearchInput -> changeSearchInput(event.input)
        is ScreenUIEvent.ChangeCurrentDestination -> changeCurRoute(event.destination)
    }

    private fun changeSearchInput(input: String) = viewModelScope.launch(coroutineDispatcher) {
        _screenUIState.value = _screenUIState.value.copy(
            searchBarInput = input
        )
    }

    private fun changeCurRoute(destination: ScreenDestinations) = viewModelScope.launch(coroutineDispatcher) {
        _screenUIState.value = _screenUIState.value.copy(
            curDestination = destination
        )
    }
}