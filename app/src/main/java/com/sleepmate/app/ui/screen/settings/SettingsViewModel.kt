package com.sleepmate.app.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.domain.usecase.DarkModeUseCase
import com.sleepmate.domain.usecase.GetEnableNotificationPushUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val darkModeUseCase: DarkModeUseCase,
    private val notificationPushUseCase: GetEnableNotificationPushUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<SettingsUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        observeSettings()
    }

    private fun observeSettings() {
        viewModelScope.launch {
            combine(
                darkModeUseCase.isDarkModeEnabled(),
                notificationPushUseCase.isNotificationPushEnabled()
            ) { isDarkMode, isNotificationPush ->
                _uiState.value = _uiState.value.copy(
                    isDarkModeEnabled = isDarkMode,
                    isNotificationPushEnabled = isNotificationPush,
                    isLoading = false
                )
            }.collect { }
        }
    }

    fun onDarkModeToggled(enabled: Boolean) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                darkModeUseCase.setDarkModeEnabled(enabled)
                _uiEvent.send(SettingsUiEvent.DarkModeChanged(enabled))
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun onNotificationPushToggled(enabled: Boolean) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                notificationPushUseCase.setNotificationPushEnabled(enabled)
                _uiEvent.send(SettingsUiEvent.NotificationPushChanged(enabled))
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}