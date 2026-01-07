package com.sleepmate.app.ui.screen.sleeptimer

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sleepmate.app.ui.theme.SleepMateTheme
import com.sleepmate.app.util.AnalyticsHelper
import com.sleepmate.domain.model.Alarm
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun SleepAlarmScreen(
    viewModel: SleepAlarmViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiState.nextAlarmTriggerTime) {
        uiState.nextAlarmTriggerTime?.let { triggerTime ->
            val message = formatTriggerTime(triggerTime)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            viewModel.onAlarmMessageShown()
        }
    }

    if (uiState.isEditAlarmDialogShown) {
        EditAlarmDialog(
            editAlarmState = uiState.editAlarmState,
            soundOptions = uiState.soundOptions,
            onDismiss = viewModel::onEditAlarmDialogDismiss,
            onSaveAlarm = viewModel::onSaveAlarm,
            onTimeChange = viewModel::onTimeChange,
            onDaySelected = viewModel::onDaySelected,
            onNameChange = viewModel::onNameChange,
            onSoundChange = viewModel::onSoundChange
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Alarma para Despertar",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "Configura tu alarma para despertarte a tiempo",
                style = MaterialTheme.typography.bodyMedium,
            )

            Spacer(modifier = Modifier.height(24.dp))

            AlarmCard(
                alarm = uiState.alarm,
                onToggle = viewModel::onToggleAlarm,
                onClick = viewModel::onEditAlarmClicked
            )
        }
    }
}

private fun formatTriggerTime(triggerTime: Long): String {
    val now = Calendar.getInstance()
    val alarmTime = Calendar.getInstance().apply { timeInMillis = triggerTime }

    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val formattedTime = timeFormat.format(alarmTime.time)

    return when {
        isSameDay(now, alarmTime) -> "Alarma programada para hoy a las $formattedTime"
        isTomorrow(now, alarmTime) -> "Alarma programada para mañana a las $formattedTime"
        else -> {
            val dayFormat = SimpleDateFormat("EEEE", Locale.forLanguageTag("es-ES"))
            val formattedDay = dayFormat.format(alarmTime.time)
                .replaceFirstChar { it.titlecase(Locale.getDefault()) }
            "Alarma programada para el $formattedDay a las $formattedTime"
        }
    }
}

private fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}

private fun isTomorrow(now: Calendar, alarm: Calendar): Boolean {
    val tomorrow = (now.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, 1) }
    return isSameDay(tomorrow, alarm)
}

private fun getOneTimeAlarmLabel(time: String): String {
    val alarmTimeParts = time.split(":").map { it.toInt() }
    val alarmCal = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, alarmTimeParts[0])
        set(Calendar.MINUTE, alarmTimeParts[1])
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val now = Calendar.getInstance()

    if (alarmCal.before(now)) {
        return "Mañana"
    }

    return "Hoy"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAlarmDialog(
    editAlarmState: EditAlarmState,
    soundOptions: List<String>,
    onDismiss: () -> Unit,
    onSaveAlarm: () -> Unit,
    onTimeChange: (Int, Int) -> Unit,
    onDaySelected: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onSoundChange: (String) -> Unit
) {
    val timePickerState = rememberTimePickerState(
        initialHour = editAlarmState.hour,
        initialMinute = editAlarmState.minute,
        is24Hour = true
    )

    LaunchedEffect(timePickerState.hour, timePickerState.minute) {
        onTimeChange(timePickerState.hour, timePickerState.minute)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Alarma") },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TimePicker(state = timePickerState)
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = editAlarmState.name,
                    onValueChange = onNameChange,
                    label = { Text("Nombre de la alarma") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                DialogDaySelector(
                    selectedDays = editAlarmState.selectedDays,
                    onDayClick = onDaySelected
                )
                Spacer(modifier = Modifier.height(16.dp))

                SoundSelector(
                    soundOptions = soundOptions,
                    selectedSound = editAlarmState.sound,
                    onSoundSelected = onSoundChange
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick =
                    {
                        AnalyticsHelper.logClick("save_alarm_button", "SleepAlarmScreen")
                        onSaveAlarm()
                    }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                AnalyticsHelper.logClick("cancel_alarm_button", "SleepAlarmScreen")

                onDismiss()
            }) {
                Text("Cancelar")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoundSelector(
    soundOptions: List<String>,
    selectedSound: String,
    onSoundSelected: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded },
    ) {
        OutlinedTextField(
            value = selectedSound,
            onValueChange = {},
            readOnly = true,
            label = { Text("Sonido") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            soundOptions.forEach { sound ->
                DropdownMenuItem(
                    text = { Text(sound) },
                    onClick = {
                        onSoundSelected(sound)
                        isExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun AlarmCard(
    alarm: Alarm,
    onToggle: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = alarm.time,
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Switch(
                    checked = alarm.isEnabled,
                    onCheckedChange = onToggle
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = alarm.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.LightGray
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (alarm.days.isEmpty()) {
                val label = if (alarm.isEnabled) {
                    getOneTimeAlarmLabel(alarm.time)
                } else {
                    "Sin programar"
                }
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            } else {
                DaySelector(selectedDays = alarm.days)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Notifications,
                    contentDescription = "Sonido",
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Sonido: ${alarm.sound}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun DaySelector(selectedDays: List<String>) {
    val days = listOf("L", "M", "X", "J", "V", "S", "D")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        days.forEach { day ->
            val isSelected = selectedDays.contains(day)
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) Color(0xFF3A5F8F) else Color.Transparent
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    color = if (isSelected) Color.White else Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun DialogDaySelector(
    selectedDays: List<String>,
    onDayClick: (String) -> Unit
) {
    val days = listOf("L", "M", "X", "J", "V", "S", "D")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        days.forEach { day ->
            val isSelected = selectedDays.contains(day)
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) Color(0xFF3A5F8F) else Color.DarkGray
                    )
                    .clickable { onDayClick(day) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1B3E)
@Composable
fun SleepAlarmScreenPreview() {
    SleepMateTheme {
        SleepAlarmScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun EditAlarmDialogPreview() {
    SleepMateTheme {
        EditAlarmDialog(
            editAlarmState = EditAlarmState(),
            soundOptions = listOf("Mañana", "Amanecer"),
            onDismiss = {},
            onSaveAlarm = {},
            onTimeChange = { _, _ -> },
            onDaySelected = {},
            onNameChange = {},
            onSoundChange = {}
        )
    }
}
