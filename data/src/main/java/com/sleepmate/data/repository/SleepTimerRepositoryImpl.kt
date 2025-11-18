package com.sleepmate.data.repository

import com.sleepmate.data.datasource.local.SleepModeDataStore
import com.sleepmate.domain.model.SleepModeState
import com.sleepmate.domain.model.SleepTimer
import com.sleepmate.domain.repository.SleepTimerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SleepTimerRepositoryImpl @Inject constructor(
    private val sleepModeDataStore: SleepModeDataStore
) : SleepTimerRepository {
    
    private val _sleepTimer = MutableStateFlow(SleepTimer())
    
    override suspend fun getSleepTimer(): Flow<SleepTimer> {
        return _sleepTimer.asStateFlow()
    }
    
    override suspend fun setSleepTimerActive(isActive: Boolean, durationMinutes: Int) {
        try {
            if (isActive) {
                val startTime = LocalDateTime.now()
                val endTime = startTime.plusMinutes(durationMinutes.toLong())
                
                _sleepTimer.value = SleepTimer(
                    isActive = true,
                    startTime = startTime,
                    endTime = endTime,
                    durationMinutes = durationMinutes
                )
            } else {
                _sleepTimer.value = SleepTimer(isActive = false)
            }
            
            Timber.d("Sleep timer state updated: active=$isActive")
            
        } catch (e: Exception) {
            Timber.e(e, "Failed to update sleep timer state")
            throw e
        }
    }
    
    override suspend fun getSleepModeState(): Flow<SleepModeState> {
        return sleepModeDataStore.getSleepModeState()
    }
    
    override suspend fun setSleepModeActivated(isActivated: Boolean, finishTimestamp: String?) {
        try {
            sleepModeDataStore.setSleepModeActivated(isActivated, finishTimestamp)
            Timber.d("Sleep mode state updated: activated=$isActivated, timestamp=$finishTimestamp")
            
        } catch (e: Exception) {
            Timber.e(e, "Failed to update sleep mode state")
            throw e
        }
    }
}