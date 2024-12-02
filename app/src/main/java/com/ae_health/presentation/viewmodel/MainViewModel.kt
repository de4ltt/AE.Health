package com.ae_health.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ae_health.domain.use_case.util.AppUseCases
import com.ae_health.presentation.mapper.toDomain
import com.ae_health.presentation.mapper.toPresentation
import com.ae_health.presentation.model.Appointment
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.model.util.Filter
import com.ae_health.presentation.model.util.ScreenDestinations
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@SuppressLint("MissingPermission")
@HiltViewModel
class MainViewModel @Inject constructor(
    private val appUseCases: AppUseCases
) : ViewModel() {

    private val _screenUIState: MutableStateFlow<ScreenUIState> = MutableStateFlow(ScreenUIState())
    val screenUIState = _screenUIState.asStateFlow()

    private val fusedLocationProviderClient: MutableState<FusedLocationProviderClient?> =
        mutableStateOf(null)

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

        ScreenUIEvent.SearchForOrganizations -> searchForOrganizations()
    }

    fun checkTrueSetLocation(fusedLocationProviderClient: FusedLocationProviderClient) =
        viewModelScope.launch(coroutineDispatcher) {
            _screenUIState.value = _screenUIState.value.copy(
                isLocationPermissionGranted = true
            )

            this@MainViewModel.fusedLocationProviderClient.value = fusedLocationProviderClient
        }

    private fun searchForOrganizations() = viewModelScope.launch(coroutineDispatcher) {

        val amenities =
            listOf("pharmacy, hospital, clinic, laboratory, doctor, dentist, blood_donation")
        val special = listOf("диспансер")
        val lat = 45.02576
        val lon = 39.034537
        val radius = 2000

        val foundOrganizations = appUseCases.GetOrganizationsNearbyUseCase(
            special = special,
            amenities = amenities,
            lat = lat,
            lon = lon,
            radius = radius
        ).map { it.toPresentation() }

        _screenUIState.value = _screenUIState.value.copy(
            foundOrganizations = foundOrganizations
        )
    }

    private fun addFavourite(organization: Organization) =
        viewModelScope.launch(coroutineDispatcher) {
            appUseCases.AddFavouriteUseCase(organization.toDomain())

            idleSwitchFavAppointBar()
        }

    private fun deleteFavourite(organization: Organization) =
        viewModelScope.launch(coroutineDispatcher) {
            appUseCases.DeleteFavouriteUseCase(organization.toDomain())

            idleSwitchFavAppointBar()
        }

    private fun addAppointment(appointment: Appointment) =
        viewModelScope.launch(coroutineDispatcher) {
            appUseCases.AddAppointmentUseCase(appointment.toDomain())
        }

    private fun deleteAppointment(appointment: Appointment) =
        viewModelScope.launch(coroutineDispatcher) {
            appUseCases.DeleteAppointmentUseCase(appointment.toDomain())
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

    private fun switchFavAppointBar(organization: Organization) =
        viewModelScope.launch(coroutineDispatcher) {
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

            appUseCases.AddHistoryUseCase(organization.toDomain())
        }

    private fun idleShowOrganization() = viewModelScope.launch(coroutineDispatcher) {
        _screenUIState.value = _screenUIState.value.copy(
            shownOrganization = null
        )
    }

    init {
        viewModelScope.launch() {

            launch {
                _screenUIState.collectLatest { state ->
                    if (state.userLocation == null && state.isLocationPermissionGranted) {

                        fusedLocationProviderClient.value?.lastLocation?.addOnSuccessListener { location ->
                            if (location != null) {

                                val latitude = location.latitude
                                val longitude = location.longitude

                                Log.d("AMERICA", "$latitude $longitude")

                                _screenUIState.value = _screenUIState.value.copy(
                                    userLocation = Pair(latitude, longitude)
                                )
                            }
                        }
                    }
                }
            }

            launch {
                _screenUIState.collectLatest { state ->
                    state.userLocation?.let { loc ->

                        val lon = loc.second
                        val lat = loc.first

                        if (state.curBestOrganizations.isEmpty())
                            _screenUIState.value = _screenUIState.value.copy(
                                curBestOrganizations = appUseCases.GetOrganizationsNearbyUseCase(
                                    lat = lat,
                                    lon = lon,
                                    radius = 500
                                ).map { it.toPresentation() }.sortedBy { it.rating }
                            )
                    }
                }
            }

            launch {
                _screenUIState.collectLatest { state ->
                    _screenUIState.value = _screenUIState.value.copy(
                        isSearchActive = state.curFilters.isNotEmpty() || state.searchBarInput.isNotEmpty()
                    )
                }
            }

            launch(Dispatchers.IO) {
                appUseCases.GetHistoryUseCase().collectLatest {

                    val presentationMap = it.map { item ->
                        Pair(
                            LocalDate.parse(item.first)!!,
                            item.second.map { el -> el.toPresentation() })
                    }.reversed().toMap()

                    _screenUIState.value = _screenUIState.value.copy(
                        historyOfVisitedOrganizations = presentationMap
                    )
                }
            }

            launch(Dispatchers.IO) {
                appUseCases.GetFavouritesUseCase().collectLatest {
                    _screenUIState.value = _screenUIState.value.copy(
                        favouriteOrganizations = it.map { it.toPresentation() }
                    )
                }
            }

            launch(Dispatchers.IO) {
                appUseCases.GetAppointmentsUseCase().collectLatest {
                    _screenUIState.value = _screenUIState.value.copy(
                        appointments = it.map { it.toPresentation() }
                    )
                }
            }
        }
    }
}

private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default