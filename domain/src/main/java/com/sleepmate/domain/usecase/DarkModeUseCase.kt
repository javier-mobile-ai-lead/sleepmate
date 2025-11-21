package com.sleepmate.domain.usecase

import com.sleepmate.domain.repository.DarkModeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DarkModeUseCase @Inject constructor(
    private val darkModeRepository: DarkModeRepository
) {
    fun isDarkModeEnabled(): Flow<Boolean?> = darkModeRepository.isDarkModeEnabled()

    suspend fun setDarkModeEnabled(enabled: Boolean) {
        darkModeRepository.setDarkModeEnabled(enabled)
    }
}
