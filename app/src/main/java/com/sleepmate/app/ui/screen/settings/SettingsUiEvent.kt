package com.sleepmate.app.ui.screen.settings

sealed class SettingsUiEvent {
    data class DarkModeChanged(val enabled: Boolean) : SettingsUiEvent()
    data class NotificationPushChanged(val enabled: Boolean) : SettingsUiEvent()
}