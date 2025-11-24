package com.sleepmate.app.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sleep_timer")

@Singleton
class SleepTimerManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    private object PreferencesKeys {
        val END_TIME_MILLIS = longPreferencesKey("sleep_timer_end_time_millis")
        val TOTAL_DURATION_MILLIS = longPreferencesKey("sleep_timer_total_duration_millis")
    }

    val timerState: Flow<TimerState> = context.dataStore.data.map { preferences ->
        val endTime = preferences[PreferencesKeys.END_TIME_MILLIS]
        val totalDuration = preferences[PreferencesKeys.TOTAL_DURATION_MILLIS]
        if (endTime != null && totalDuration != null && endTime > System.currentTimeMillis()) {
            TimerState.Active(endTime, totalDuration)
        } else {
            TimerState.Inactive
        }
    }

    suspend fun startTimer(durationMinutes: Int) {
        val durationMillis = durationMinutes * 60 * 1000L
        val endTime = System.currentTimeMillis() + durationMillis

        val pendingIntent = getPendingIntent()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            endTime,
            pendingIntent
        )

        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.END_TIME_MILLIS] = endTime
            preferences[PreferencesKeys.TOTAL_DURATION_MILLIS] = durationMillis
        }

        // Start the foreground service
        val serviceIntent = Intent(context, SleepTimerService::class.java)
        context.startService(serviceIntent)
    }

    suspend fun cancelTimer() {
        val pendingIntent = getPendingIntent()
        alarmManager.cancel(pendingIntent)
        pendingIntent.cancel()

        context.dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.END_TIME_MILLIS)
            preferences.remove(PreferencesKeys.TOTAL_DURATION_MILLIS)
        }

        // Stop the foreground service
        val serviceIntent = Intent(context, SleepTimerService::class.java)
        context.stopService(serviceIntent)
    }

    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(context, SleepTimerReceiver::class.java).apply {
            action = ACTION_SLEEP_TIMER_FINISHED
        }
        return PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    companion object {
        const val ACTION_SLEEP_TIMER_FINISHED = "com.sleepmate.app.action.SLEEP_TIMER_FINISHED"
        private const val REQUEST_CODE = 1987
    }
}

sealed class TimerState {
    data class Active(val endTimeMillis: Long, val totalDurationMillis: Long) : TimerState()
    object Inactive : TimerState()
}
