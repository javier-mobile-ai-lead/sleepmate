package com.sleepmate.app.ui.screen.sleeptimer

import android.content.ComponentName
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.app.service.DeviceAdminManager
import com.sleepmate.app.service.DoNotDisturbManager
import com.sleepmate.app.service.SleepTimerManager
import com.sleepmate.app.service.TimerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class SleepTimerUiState(
    val isTimerActive: Boolean = false,
    val timeRemaining: String = "25:00",
    val progress: Float = 0f,
    val sleepModeActivated: Boolean = false,
    val timerWasCancelled: Boolean = false,
    val hasNotificationPolicyAccess: Boolean = false,
    val isDeviceAdminEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedDurationMinutes: Int = 25
)

@HiltViewModel
class SleepTimerViewModel @Inject constructor(
    private val sleepTimerManager: SleepTimerManager,
    private val doNotDisturbManager: DoNotDisturbManager,
    private val deviceAdminManager: DeviceAdminManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(SleepTimerUiState())
    val uiState: StateFlow<SleepTimerUiState> = _uiState.asStateFlow()

    private var tickerJob: Job? = null

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
        sleepTimerManager.timerState
            .onEach { state ->
                tickerJob?.cancel()
                when (state) {
                    is TimerState.Active -> {
                        _uiState.value = _uiState.value.copy(
                            isTimerActive = true,
                            sleepModeActivated = false,
                            timerWasCancelled = false
                        )
                        startUiTicker(state.endTimeMillis, state.totalDurationMillis)
                    }
                    is TimerState.Inactive -> {
                        val sleepModeJustActivated = _uiState.value.isTimerActive && !_uiState.value.timerWasCancelled
                        _uiState.value = _uiState.value.copy(
                            isTimerActive = false,
                            progress = 0f,
                            timeRemaining = formatTimeFromMinutes(_uiState.value.selectedDurationMinutes),
                            sleepModeActivated = sleepModeJustActivated
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun startUiTicker(endTimeMillis: Long, totalDurationMillis: Long) {
        tickerJob = viewModelScope.launch {
            while (true) {
                val remainingMillis = endTimeMillis - System.currentTimeMillis()
                if (remainingMillis <= 0) {
                    _uiState.value = _uiState.value.copy(timeRemaining = "00:00", progress = 1f)
                    break
                }

                val progress = 1f - (remainingMillis.toFloat() / totalDurationMillis.toFloat()).coerceIn(0f, 1f)

                _uiState.value = _uiState.value.copy(
                    timeRemaining = formatTimeFromMillis(remainingMillis),
                    progress = progress
                )
                delay(1000)
            }
        }
    }

    fun startSleepTimer() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    timerWasCancelled = false
                )
                sleepTimerManager.startTimer(_uiState.value.selectedDurationMinutes)
            } catch (e: Exception) {
                Timber.e(e, "Failed to start timer")
                _uiState.value = _uiState.value.copy(error = "Error al iniciar el temporizador")
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun cancelSleepTimer() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    timerWasCancelled = true
                )
                sleepTimerManager.cancelTimer()
            } catch (e: Exception) {
                Timber.e(e, "Failed to cancel timer")
                _uiState.value = _uiState.value.copy(error = "Error al cancelar el temporizador")
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun onDurationChange(newDurationMinutes: Int) {
        if (!_uiState.value.isTimerActive) {
            _uiState.value = _uiState.value.copy(
                selectedDurationMinutes = newDurationMinutes,
                timeRemaining = formatTimeFromMinutes(newDurationMinutes)
            )
        }
    }

    private fun formatTimeFromMinutes(minutes: Int): String {
        return String.format("%02d:00", minutes)
    }

    private fun formatTimeFromMillis(millis: Long): String {
        val totalSeconds = millis / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    // --- Permissions --- (These methods remain the same)

    fun checkNotificationPolicyAccess() {
        viewModelScope.launch {
            val hasAccess = doNotDisturbManager.hasNotificationPolicyAccess()
            _uiState.value = _uiState.value.copy(hasNotificationPolicyAccess = hasAccess)
        }
    }

    fun checkDeviceAdminStatus() {
        _uiState.value = _uiState.value.copy(isDeviceAdminEnabled = deviceAdminManager.isDeviceAdminActive())
    }

    fun openNotificationPolicySettings() {
        doNotDisturbManager.openNotificationPolicySettings()
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
