package com.sleepmate.domain.usecase.health

import com.sleepmate.domain.repository.HealthDataRepository
import javax.inject.Inject

class GetGrantedHealthPermissionsUseCase @Inject constructor(
    private val repository: HealthDataRepository
) {
    suspend operator fun invoke(): Set<String> {
        return repository.getGrantedPermissions()
    }
}
