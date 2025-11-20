package com.sleepmate.app.ui.screen.sleeptimer

import android.content.ComponentName
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.app.service.DeviceAdminManager
import com.sleepmate.app.service.DoNotDisturbManager
import com.sleepmate.app.service.SleepNotificationService
import com.sleepmate.app.service.SleepTimerAlarmManager
import com.sleepmate.domain.model.SleepModeState
import com.sleepmate.domain.model.SleepTimer
import com.sleepmate.domain.repository.SleepTimerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

data class SleepTimerUiState(
    val isTimerActive: Boolean = false,
    val timeRemaining: String = "25:00",
    val progress: Float = 0f,
    val sleepModeActivated: Boolean = false,
    val hasNotificationPolicyAccess: Boolean = false,
    val isDeviceAdminEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedDurationMinutes: Int = 25,

)

@HiltViewModel
class SleepTimerViewModel @Inject constructor(
    private val sleepTimerRepository: SleepTimerRepository,
    private val sleepTimerAlarmManager: SleepTimerAlarmManager,
    private val doNotDisturbManager: DoNotDisturbManager,
    private val sleepNotificationService: SleepNotificationService,
    private val deviceAdminManager: DeviceAdminManager
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(SleepTimerUiState())
    val uiState: StateFlow<SleepTimerUiState> = _uiState.asStateFlow()

    val adminComponentName: ComponentName
        get() = deviceAdminManager.adminComponent
    
    init {
        observeTimerState()
        checkInitialPermissions()
    }

    private fun checkInitialPermissions() {
        checkNotificationPolicyAccess()
        checkDeviceAdminStatus() 
    }

    private fun observeTimerState() {
        viewModelScope.launch {
            try {
                combine(
                    sleepTimerRepository.getSleepTimer(),
                    sleepTimerRepository.getSleepModeState(),
                    tickerFlow(1_000)
                ) { sleepTimer, sleepModeState, _ ->
                    updateUiState(sleepTimer, sleepModeState)
                }.collect()
            } catch (e: Exception) {
                Timber.e(e, "Error observing timer state")
                _uiState.value = _uiState.value.copy(
                    error = "Error al observar el estado del temporizador"
                )
            }
        }
    }

    private fun tickerFlow(periodMillis: Long): Flow<Unit> = flow {
        while (true) {
            emit(Unit)
            delay(periodMillis)
        }
    }
    
    private fun updateUiState(sleepTimer: SleepTimer, sleepModeState: SleepModeState) {
        val timeRemaining = if (sleepTimer.isActive && sleepTimer.endTime != null) {
            calculateTimeRemaining(sleepTimer.endTime!!)
        } else {
            String.format("%02d:00", _uiState.value.selectedDurationMinutes)
        }
        
        val progress = if (sleepTimer.isActive && sleepTimer.startTime != null && sleepTimer.endTime != null) {
            calculateProgress(sleepTimer.startTime!!, sleepTimer.endTime!!)
        } else {
            0f
        }
        
        _uiState.value = _uiState.value.copy(
            isTimerActive = sleepTimer.isActive,
            timeRemaining = timeRemaining,
            progress = progress,
            sleepModeActivated = sleepModeState.isActivated,
            isLoading = false,
            error = null
        )
    }
    
    private fun calculateTimeRemaining(endTime: LocalDateTime): String {
        val now = LocalDateTime.now()
        val duration = Duration.between(now, endTime)
        
        return if (duration.isNegative || duration.isZero) {
            "00:00"
        } else {
            val minutes = duration.toMinutes()
            val seconds = duration.seconds % 60
            String.format("%02d:%02d", minutes, seconds)
        }
    }
    
    private fun calculateProgress(startTime: LocalDateTime, endTime: LocalDateTime): Float {
        val now = LocalDateTime.now()
        val totalDuration = Duration.between(startTime, endTime).seconds.toFloat()
        val elapsed = Duration.between(startTime, now).seconds.toFloat()
        
        return if (totalDuration > 0) {
            (elapsed / totalDuration).coerceIn(0f, 1f)
        } else {
            0f
        }
    }
    
    fun startSleepTimer() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                val durationMinutes = _uiState.value.selectedDurationMinutes
                sleepTimerRepository.setSleepTimerActive(true, durationMinutes)
                sleepTimerAlarmManager.startSleepTimer(durationMinutes)
                Timber.d("Sleep timer started successfully")
                
            } catch (e: Exception) {
                Timber.e(e, "Failed to start sleep timer")
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error al iniciar el temporizador"
                )
            }
        }
    }
    
    fun cancelSleepTimer() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                sleepTimerRepository.setSleepTimerActive(false)
                sleepTimerAlarmManager.cancelSleepTimer()
                sleepNotificationService.cancelSleepNotification()
                Timber.d("Sleep timer cancelled successfully")
                
            } catch (e: Exception) {
                Timber.e(e, "Failed to cancel sleep timer")
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error al cancelar el temporizador"
                )
            }
        }
    }
    
    fun checkNotificationPolicyAccess() {
        viewModelScope.launch {
            try {
                val hasAccess = doNotDisturbManager.hasNotificationPolicyAccess()
                _uiState.value = _uiState.value.copy(hasNotificationPolicyAccess = hasAccess)
                
            } catch (e: Exception) {
                Timber.e(e, "Failed to check notification policy access")
            }
        }
    }

    fun checkDeviceAdminStatus() {
        _uiState.value = _uiState.value.copy(isDeviceAdminEnabled = deviceAdminManager.isDeviceAdminActive())
    }
    
    fun openNotificationPolicySettings() {
        viewModelScope.launch {
            try {
                doNotDisturbManager.openNotificationPolicySettings()
                
            } catch (e: Exception) {
                Timber.e(e, "Failed to open notification policy settings")
                _uiState.value = _uiState.value.copy(
                    error = "Error al abrir configuración de permisos"
                )
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun onDurationChange(newDurationMinutes: Int) {
        if (!_uiState.value.isTimerActive) {
            _uiState.value = _uiState.value.copy(
                selectedDurationMinutes = newDurationMinutes,
                timeRemaining = String.format("%02d:00", newDurationMinutes)
            )
        }
    }
}