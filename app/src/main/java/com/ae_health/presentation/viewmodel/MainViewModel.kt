package com.ae_health.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ae_health.presentation.model.Appointment
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.model.util.Filter
import com.ae_health.presentation.model.util.ScreenDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _screenUIState: MutableStateFlow<ScreenUIState> = MutableStateFlow(ScreenUIState())
    val screenUIState = _screenUIState.asStateFlow()

    fun onEvent(event: ScreenUIEvent) = when (event) {
        is ScreenUIEvent.ChangeSearchInput -> changeSearchInput(event.input)
        is ScreenUIEvent.SwitchFilter -> switchFilter(event.filter)

        is ScreenUIEvent.ChangeCurrentDestination -> changeCurRoute(event.destination)

        ScreenUIEvent.IdleShowOrganization -> idleShowOrganization()
        is ScreenUIEvent.ShowOrganization -> showOrganization(event.organization)

        is ScreenUIEvent.AddAppointment -> addAppointment(event.appointment)
        is ScreenUIEvent.DeleteAppointment -> deleteAppointment(event.appointment)
        ScreenUIEvent.SwitchAddAppointment -> switchAddAppointment()

        is ScreenUIEvent.SwitchFavAppointBar -> switchFavAppointBar(event.organization)
        ScreenUIEvent.IdleSwitchFavAppointBar -> idleSwitchFavAppointBar()

        is ScreenUIEvent.AddFavourite -> addFavourite(event.organization)
        is ScreenUIEvent.DeleteFavourite -> deleteFavourite(event.organization)
    }

    private fun addFavourite(organization: Organization) = viewModelScope.launch(coroutineDispatcher) {

    }

    private fun deleteFavourite(organization: Organization) = viewModelScope.launch(coroutineDispatcher) {

    }

    private fun addAppointment(appointment: Appointment) = viewModelScope.launch(coroutineDispatcher) {

    }

    private fun deleteAppointment(appointment: Appointment) = viewModelScope.launch(coroutineDispatcher) {

    }

    private fun idleSwitchFavAppointBar() = viewModelScope.launch(coroutineDispatcher) {
        _screenUIState.value = _screenUIState.value.copy(
            addFavAppointOrganization = null
        )
    }

    private fun switchAddAppointment() = viewModelScope.launch(coroutineDispatcher) {
        _screenUIState.value = _screenUIState.value.copy(
            isAddAppointmentBar = !_screenUIState.value.isAddAppointmentBar
        )
    }

    private fun switchFavAppointBar(organization: Organization) = viewModelScope.launch(coroutineDispatcher) {
        _screenUIState.value = _screenUIState.value.copy(
            addFavAppointOrganization = organization
        )
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

private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default