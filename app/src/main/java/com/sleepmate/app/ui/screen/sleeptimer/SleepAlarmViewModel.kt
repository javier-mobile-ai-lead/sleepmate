package com.sleepmate.app.ui.screen.sleeptimer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.app.service.WakeUpAlarmManager
import com.sleepmate.domain.model.Alarm
import com.sleepmate.domain.repository.AlarmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SleepAlarmViewModel @Inject constructor(
    private val alarmManager: WakeUpAlarmManager,
    private val alarmRepository: AlarmRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SleepAlarmState())
    val uiState: StateFlow<SleepAlarmState> = _uiState.asStateFlow()

    private val soundOptions = listOf("Mañana", "Amanecer", "Bosque", "Olas", "Tiktak", "Silencio")

    init {
        viewModelScope.launch {
            val alarm = alarmRepository.getAlarm().first()
            _uiState.update {
                it.copy(
                    soundOptions = soundOptions,
                    alarm = alarm
                )
            }
        }
    }

    fun onToggleAlarm(isEnabled: Boolean) {
        viewModelScope.launch {
            val updatedAlarm = _uiState.value.alarm.copy(isEnabled = isEnabled)
            alarmRepository.saveAlarm(updatedAlarm)

            val triggerTime = if (updatedAlarm.isEnabled) {
                alarmManager.schedule(updatedAlarm)
            } else {
                alarmManager.cancel()
                null // Alarm cancelled, no trigger time
            }
            _uiState.update {
                it.copy(
                    alarm = updatedAlarm,
                    nextAlarmTriggerTime = triggerTime
                )
            }
        }
    }

    fun onAlarmMessageShown() {
        _uiState.update { it.copy(nextAlarmTriggerTime = null) }
    }

    fun onEditAlarmClicked() {
        _uiState.update { currentState ->
            val currentAlarm = currentState.alarm
            val timeParts = currentAlarm.time.split(":").mapNotNull { it.toIntOrNull() }
            val hour = if (timeParts.size == 2) timeParts[0] else 7
            val minute = if (timeParts.size == 2) timeParts[1] else 0

            currentState.copy(
                isEditAlarmDialogShown = true,
                editAlarmState = EditAlarmState(
                    hour = hour,
                    minute = minute,
                    selectedDays = currentAlarm.days,
                    name = currentAlarm.name,
                    sound = currentAlarm.sound
                )
            )
        }
    }

    fun onEditAlarmDialogDismiss() {
        _uiState.update { it.copy(isEditAlarmDialogShown = false) }
    }

    fun onTimeChange(hour: Int, minute: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                editAlarmState = currentState.editAlarmState.copy(hour = hour, minute = minute)
            )
        }
    }

    fun onDaySelected(day: String) {
        _uiState.update { currentState ->
            val currentDays = currentState.editAlarmState.selectedDays.toMutableList()
            if (currentDays.contains(day)) {
                currentDays.remove(day)
            } else {
                currentDays.add(day)
            }
            currentState.copy(
                editAlarmState = currentState.editAlarmState.copy(selectedDays = currentDays)
            )
        }
    }

    fun onNameChange(name: String) {
        _uiState.update { currentState ->
            currentState.copy(editAlarmState = currentState.editAlarmState.copy(name = name))
        }
    }

    fun onSoundChange(sound: String) {
        _uiState.update { currentState ->
            currentState.copy(editAlarmState = currentState.editAlarmState.copy(sound = sound))
        }
    }

    fun onSaveAlarm() {
        viewModelScope.launch {
            val currentState = _uiState.value
            val editedState = currentState.editAlarmState
            val updatedAlarm = currentState.alarm.copy(
                time = formatTime(editedState.hour, editedState.minute),
                name = editedState.name.ifBlank { "Alarma" },
                days = editedState.selectedDays,
                sound = editedState.sound
            )
            alarmRepository.saveAlarm(updatedAlarm)

            // After saving, reschedule the alarm if it's enabled
            val triggerTime = if (updatedAlarm.isEnabled) {
                alarmManager.schedule(updatedAlarm)
            } else {
                null // Not enabled, so no trigger time
            }
            _uiState.update {
                it.copy(
                    alarm = updatedAlarm,
                    isEditAlarmDialogShown = false,
                    nextAlarmTriggerTime = triggerTime // Update the trigger time
                )
            }
        }
    }

    private fun formatTime(hour: Int, minute: Int): String {
        return "%02d:%02d".format(hour, minute)
    }
}

data class SleepAlarmState(
    val alarm: Alarm = Alarm(),
    val isEditAlarmDialogShown: Boolean = false,
    val editAlarmState: EditAlarmState = EditAlarmState(),
    val soundOptions: List<String> = emptyList(),
    val nextAlarmTriggerTime: Long? = null // New field to hold the confirmation time
)

data class EditAlarmState(
    val hour: Int = 7,
    val minute: Int = 0,
    val selectedDays: List<String> = emptyList(),
    val name: String = "",
    val sound: String = ""
)
