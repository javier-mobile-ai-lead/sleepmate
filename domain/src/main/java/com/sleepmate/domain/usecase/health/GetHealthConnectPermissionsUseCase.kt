package com.sleepmate.domain.usecase.health

import com.sleepmate.domain.repository.HealthDataRepository
import javax.inject.Inject

/**
 * Use case to retrieve the set of required permissions for Health Connect.
 */
class GetHealthConnectPermissionsUseCase @Inject constructor(
    private val healthDataRepository: HealthDataRepository
) {
    /**
     * Returns the required Health Connect permissions as defined in the repository.
     */
    operator fun invoke(): Set<String> {
        return healthDataRepository.getRequiredPermissions()
    }
}
