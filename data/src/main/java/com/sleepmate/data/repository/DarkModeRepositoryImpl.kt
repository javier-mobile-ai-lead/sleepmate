package com.sleepmate.data.repository

import com.sleepmate.data.datasource.local.DarkModePreferences
import com.sleepmate.domain.repository.DarkModeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DarkModeRepositoryImpl @Inject constructor(
    private val darkModePreferences: DarkModePreferences
) : DarkModeRepository {
    
    override fun isDarkModeEnabled(): Flow<Boolean?> {
        return darkModePreferences.isDarkModeEnabled
    }
    
    override suspend fun setDarkModeEnabled(enabled: Boolean) {
        darkModePreferences.setDarkModeEnabled(enabled)
    }
}