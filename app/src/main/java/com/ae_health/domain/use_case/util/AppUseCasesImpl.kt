package com.ae_health.domain.use_case.util

import com.ae_health.domain.use_case.AddAppointmentUseCase
import com.ae_health.domain.use_case.AddFavouriteUseCase
import com.ae_health.domain.use_case.AddHistoryUseCase
import com.ae_health.domain.use_case.DeleteAppointmentUseCase
import com.ae_health.domain.use_case.DeleteFavouriteUseCase
import com.ae_health.domain.use_case.GetAppointmentsUseCase
import com.ae_health.domain.use_case.GetFavouritesUseCase
import com.ae_health.domain.use_case.GetHistoryUseCase
import com.ae_health.domain.use_case.GetOrganizationsNearbyUseCase
import javax.inject.Inject

data class AppUseCasesImpl @Inject constructor(
    override val AddAppointmentUseCase: AddAppointmentUseCase,
    override val AddHistoryUseCase: AddHistoryUseCase,
    override val DeleteAppointmentUseCase: DeleteAppointmentUseCase,
    override val GetAppointmentsUseCase: GetAppointmentsUseCase,
    override val GetFavouritesUseCase: GetFavouritesUseCase,
    override val GetHistoryUseCase: GetHistoryUseCase,
    override val GetOrganizationsNearbyUseCase: GetOrganizationsNearbyUseCase,
    override val AddFavouriteUseCase: AddFavouriteUseCase,
    override val DeleteFavouriteUseCase: DeleteFavouriteUseCase
): AppUseCases
