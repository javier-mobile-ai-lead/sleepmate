package com.sleepmate.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.app.service.DeviceAdminManager
import com.sleepmate.domain.usecase.DarkModeUseCase
import com.sleepmate.domain.usecase.UserProgressTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val darkModeUseCase: DarkModeUseCase,
    private val userProgressTracker: UserProgressTracker,
    private val deviceAdminManager: DeviceAdminManager
): ViewModel() {
    // Now nullable to represent three states: null (system), true (dark), false (light)
    private val _isDarkTheme = MutableStateFlow<Boolean?>(null)
    val isDarkTheme: StateFlow<Boolean?> = _isDarkTheme.asStateFlow()

    // State for Device Admin
    private val _isDeviceAdminEnabled = MutableStateFlow(false)
    val isDeviceAdminEnabled: StateFlow<Boolean> = _isDeviceAdminEnabled.asStateFlow()

    init {
        viewModelScope.launch {
           // This now expects the use case to return Flow<Boolean?> so we can distinguish
           // between "not set" (null) and "set to light theme" (false).
           darkModeUseCase.isDarkModeEnabled().collect{
                _isDarkTheme.value = it
            }
        }
        
        viewModelScope.launch {
            userProgressTracker.trackAndResetIfNeeded()
        }
        
        // Check initial admin status
        refreshDeviceAdminStatus()
    }

    fun checkNewDay() {
        viewModelScope.launch {
            userProgressTracker.trackAndResetIfNeeded()
        }
    }

    fun setDarkTheme(enabled: Boolean) {
        viewModelScope.launch {
            darkModeUseCase.setDarkModeEnabled(enabled)
        }
    }

    // Function to re-check the admin status
    fun refreshDeviceAdminStatus() {
        _isDeviceAdminEnabled.value = deviceAdminManager.isDeviceAdminActive()
    }
}