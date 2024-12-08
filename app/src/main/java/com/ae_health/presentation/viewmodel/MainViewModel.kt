package com.ae_health.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ae_health.domain.use_case.util.AppUseCases
import com.ae_health.presentation.mapper.toDomain
import com.ae_health.presentation.mapper.toPresentation
import com.ae_health.presentation.model.Appointment
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.model.event.GPSEvent
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.GPSState
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.model.util.Filter
import com.ae_health.presentation.model.util.FilterType
import com.ae_health.presentation.model.util.ScreenDestinations
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appUseCases: AppUseCases
) : ViewModel() {

    private val _screenUIState = MutableStateFlow(ScreenUIState())
    val screenUIState = _screenUIState.asStateFlow()

    private val _gpsState = MutableStateFlow(GPSState())
    val gpsState = _gpsState.asStateFlow()

    private val coroutineDispatcher = Dispatchers.Default
    private var hasLoadedOrganizations = false

    fun onEvent(event: GPSEvent) = when (event) {
        GPSEvent.PermissionGranted -> permissionGranted()
        GPSEvent.PermissionDenied -> permissionDenied()
    }

    fun onEvent(event: ScreenUIEvent) {
        when (event) {
            is ScreenUIEvent.ChangeSearchInput -> updateSearchInput(event.input)
            is ScreenUIEvent.SwitchFilter -> switchFilter(event.filter)
            is ScreenUIEvent.ChangeCurrentDestination -> changeDestination(event.destination)
            is ScreenUIEvent.ShowOrganization -> showOrganization(event.organization)
            is ScreenUIEvent.AddFavourite -> addFavourite(event.organization)
            is ScreenUIEvent.DeleteFavourite -> deleteFavourite(event.organization)
            is ScreenUIEvent.SearchForOrganizations -> searchForOrganizations()
            is ScreenUIEvent.AddAppointment -> addAppointment(event.appointment)
            is ScreenUIEvent.DeleteAppointment -> deleteAppointment(event.appointment)
            is ScreenUIEvent.SwitchFavAppointBar -> switchFavAppointBar(event.organization)
            ScreenUIEvent.IdleShowOrganization -> idleShowOrganization()
            ScreenUIEvent.IdleSwitchFavAppointBar -> idleSwitchFavAppointBar()
            ScreenUIEvent.SwitchAddAppointment -> switchAddAppointment()
        }
    }

    init {
        viewModelScope.launch(coroutineDispatcher) {
            launch { observeStateChanges() }
            launch { loadInitialData() }
        }
    }

    private fun permissionGranted() = updateGPSState {
        copy(isPermissionGranted = true)
    }

    private fun permissionDenied() = updateGPSState {
        copy(isPermissionGranted = false)
    }

    private suspend fun observeStateChanges() {
        withContext(coroutineDispatcher) {
            launch {
                gpsState.collectLatest { state ->
                    state.userPosition?.let {
                        if (_screenUIState.value.historyOfVisitedOrganizations.isNotEmpty())
                            hasLoadedOrganizations = true
                        if (!hasLoadedOrganizations) {
                            hasLoadedOrganizations = true
                            fetchOrganizationsNearby(it.latitude, it.longitude, 700)
                        }
                    }
                }
            }

            launch {
                screenUIState.collectLatest { state ->
                    val isSearchActive =
                        state.curFilters.isNotEmpty() || state.searchBarInput.isNotEmpty()
                    updateUIState { copy(isSearchActive = isSearchActive) }
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun fetchCurrentLocation(context: Context) {
        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                if (location != null) {
                    viewModelScope.launch {
                        _gpsState.value = _gpsState.value.copy(
                            userPosition = location
                        )
                    }
                } else {
                    Log.e("LocationError", "Location is null")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("LocationError", "Failed to get location: ${exception.message}")
            }
    }

    private fun loadInitialData() = viewModelScope.launch(Dispatchers.IO) {

        launch {
            appUseCases.GetHistoryUseCase().collectLatest { history ->
                val presentationMap = history.map { (date, orgs) ->
                    LocalDate.parse(date) to orgs.map { it.toPresentation() }
                }.reversed().toMap()

                updateUIState {
                    copy(
                        historyOfVisitedOrganizations = presentationMap,
                        latestVisitedOrganizations = presentationMap.flatMap { it.value }
                            .takeLast(7)
                    )
                }
            }
        }

        launch {
            appUseCases.GetFavouritesUseCase().collectLatest { favourites ->
                updateUIState {
                    copy(favouriteOrganizations = favourites.map { it.toPresentation() })
                }
            }
        }

        launch {
            appUseCases.GetAppointmentsUseCase().collectLatest { appointments ->
                updateUIState {
                    copy(appointments = appointments.map { it.toPresentation() })
                }
            }
        }
    }

    private suspend fun fetchOrganizationsNearby(lat: Double, lon: Double, radius: Int) {
        val organizations = appUseCases.GetOrganizationsNearbyUseCase(
            lat = lat, lon = lon, radius = radius
        ).map { it.toPresentation() }

        updateUIState { copy(curBestOrganizations = organizations) }
    }

    private fun addAppointment(appointment: Appointment) =
        viewModelScope.launch(coroutineDispatcher) {
            appUseCases.AddAppointmentUseCase(appointment.toDomain())
        }

    private fun deleteAppointment(appointment: Appointment) =
        viewModelScope.launch(coroutineDispatcher) {
            appUseCases.DeleteAppointmentUseCase(appointment.toDomain())
        }

    private fun idleShowOrganization() = updateUIState {
        copy(shownOrganization = null)
    }

    private fun idleSwitchFavAppointBar() = updateUIState {
        copy(addFavAppointOrganization = null)
    }

    private fun switchAddAppointment() = updateUIState {
        copy(isAddAppointmentBar = !isAddAppointmentBar)
    }

    private fun switchFavAppointBar(organization: Organization) = updateUIState {
        copy(
            addFavAppointOrganization = organization,
            isAddAppointmentBar = false
        )
    }

    private fun searchForOrganizations() = viewModelScope.launch(coroutineDispatcher) {
        val state = _screenUIState.value
        val gpsState = _gpsState.value
        val lat = gpsState.userPosition?.latitude
        val lon = gpsState.userPosition?.longitude
        val radius =
            state.curFilters.find { it.type == FilterType.DISTANCE }?.engName?.toIntOrNull() ?: 300
        val amenities = state.curFilters.filter { it.type == FilterType.TYPE }.map { it.engName }
        val special = state.curFilters.filter { it.type == FilterType.SPECIAL }.map { it.engName }
        val query = state.searchBarInput

        if (lat != null && lon != null) {
            val organizations = appUseCases.GetOrganizationsNearbyUseCase(
                lat = lat,
                lon = lon,
                radius = radius,
                amenities = amenities,
                special = special,
                query = query
            ).map { it.toPresentation() }

            updateUIState { copy(foundOrganizations = organizations) }
        }
    }

    private fun updateSearchInput(input: String) = updateUIState {
        copy(searchBarInput = input)
    }

    private fun changeDestination(destination: ScreenDestinations) = updateUIState {
        copy(curDestination = destination)
    }

    private fun switchFilter(filter: Filter?) = updateUIState {
        val newFilters = if (filter != null) {
            if (curFilters.contains(filter)) curFilters - filter else curFilters + filter
        } else {
            emptyList()
        }
        copy(curFilters = newFilters)
    }

    private fun showOrganization(organization: Organization) = viewModelScope.launch {
        appUseCases.AddHistoryUseCase(organization.toDomain())
        updateUIState { copy(shownOrganization = organization) }
    }

    private fun addFavourite(organization: Organization) = viewModelScope.launch {
        appUseCases.AddFavouriteUseCase(organization.toDomain())
    }

    private fun deleteFavourite(organization: Organization) = viewModelScope.launch {
        appUseCases.DeleteFavouriteUseCase(organization.toDomain())
    }

    private fun updateUIState(update: ScreenUIState.() -> ScreenUIState) {
        _screenUIState.value = _screenUIState.value.update()
    }

    private fun updateGPSState(update: GPSState.() -> GPSState) =
        viewModelScope.launch(coroutineDispatcher) {
            _gpsState.value = _gpsState.value.update()
        }
}