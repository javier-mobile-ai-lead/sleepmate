package com.sleepmate.app.ui.screen.settings

data class SettingsUiState(
    val isDarkModeEnabled: Boolean = false,
    val isNotificationPushEnabled: Boolean = true,
    val isLoading: Boolean = false
)