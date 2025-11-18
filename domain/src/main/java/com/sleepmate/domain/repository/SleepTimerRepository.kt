package com.sleepmate.domain.repository

import com.sleepmate.domain.model.SleepModeState
import com.sleepmate.domain.model.SleepTimer
import kotlinx.coroutines.flow.Flow

interface SleepTimerRepository {
    suspend fun getSleepTimer(): Flow<SleepTimer>
    suspend fun setSleepTimerActive(isActive: Boolean, durationMinutes: Int = 25)
    suspend fun getSleepModeState(): Flow<SleepModeState>
    suspend fun setSleepModeActivated(isActivated: Boolean, finishTimestamp: String? = null)
}