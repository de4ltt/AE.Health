package com.ae_health.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.model.util.Filter
import com.ae_health.presentation.model.util.ScreenDestinations
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    private val _screenUIState: MutableStateFlow<ScreenUIState> = MutableStateFlow(ScreenUIState())
    val screenUIState = _screenUIState.asStateFlow()

    fun onEvent(event: ScreenUIEvent) = when (event) {
        is ScreenUIEvent.ChangeSearchInput -> changeSearchInput(event.input)
        is ScreenUIEvent.SwitchFilter -> switchFilter(event.filter)

        is ScreenUIEvent.ChangeCurrentDestination -> changeCurRoute(event.destination)

        ScreenUIEvent.IdleShowOrganization -> idleShowOrganization()
        is ScreenUIEvent.ShowOrganization -> showOrganization(event.organization)
    }

    private fun changeSearchInput(input: String) = viewModelScope.launch(coroutineDispatcher) {
        _screenUIState.value = _screenUIState.value.copy(
            searchBarInput = input
        )
    }

    private fun changeCurRoute(destination: ScreenDestinations) =
        viewModelScope.launch(coroutineDispatcher) {
            _screenUIState.value = _screenUIState.value.copy(
                curDestination = destination
            )
        }

    private fun switchFilter(filter: Filter?) = viewModelScope.launch(coroutineDispatcher) {

        val filters = _screenUIState.value.curFilters

        if (filter != null) {
            _screenUIState.value = _screenUIState.value.copy(
                curFilters = if (filters.contains(filter)) filters.minus(filter) else filters.plus(
                    filter
                )
            )
        } else
            _screenUIState.value = _screenUIState.value.copy(
                curFilters = emptyList()
            )
    }

    private fun showOrganization(organization: Organization) =
        viewModelScope.launch(coroutineDispatcher) {
            _screenUIState.value = _screenUIState.value.copy(
                shownOrganization = organization
            )
        }

    private fun idleShowOrganization() = viewModelScope.launch(coroutineDispatcher) {
        _screenUIState.value = _screenUIState.value.copy(
            shownOrganization = null
        )
    }

    init {

        viewModelScope.launch(coroutineDispatcher) {
            _screenUIState.collectLatest { state ->
                _screenUIState.value = _screenUIState.value.copy(
                    isSearchActive = state.curFilters.isNotEmpty() || state.searchBarInput.isNotEmpty()
                )
            }
        }
    }
}