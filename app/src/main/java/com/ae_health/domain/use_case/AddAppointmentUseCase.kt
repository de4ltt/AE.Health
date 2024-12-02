package com.ae_health.domain.use_case

import com.ae_health.domain.model.AppointmentDomain
import com.ae_health.domain.repository.OrganizationRepository
import javax.inject.Inject

class AddAppointmentUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
) {

    suspend operator fun invoke(appointmentDomain: AppointmentDomain) =
        organizationRepository.addAppointment(appointmentDomain)
}