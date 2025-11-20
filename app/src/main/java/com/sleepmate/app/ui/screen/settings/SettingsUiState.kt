package com.sleepmate.app.ui.screen.settings

data class SettingsUiState(
    val isDarkModeEnabled: Boolean? = null,
    val isNotificationPushEnabled: Boolean = true,
    val isLoading: Boolean = false
)