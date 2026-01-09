package com.sleepmate.domain.usecase.health

import com.sleepmate.domain.repository.HealthDataRepository
import javax.inject.Inject

class CheckHealthConnectAvailabilityUseCase @Inject constructor(
    private val healthDataRepository: HealthDataRepository
) {
    suspend operator fun invoke() = healthDataRepository.isHealthConnectAvailable()
}
