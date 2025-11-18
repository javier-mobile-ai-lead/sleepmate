package com.sleepmate.domain.repository

import kotlinx.coroutines.flow.Flow

interface DarkModeRepository {
    fun isDarkModeEnabled(): Flow<Boolean>
    suspend fun setDarkModeEnabled(enabled: Boolean)
}