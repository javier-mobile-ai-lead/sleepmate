package com.sleepmate.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.sleepmate.domain.model.Alarm
import com.sleepmate.domain.repository.AlarmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AlarmRepository {

    private object PreferencesKeys {
        val ALARM_TIME = stringPreferencesKey("alarm_time")
        val ALARM_NAME = stringPreferencesKey("alarm_name")
        val ALARM_DAYS = stringSetPreferencesKey("alarm_days")
        val ALARM_SOUND = stringPreferencesKey("alarm_sound")
        val ALARM_ENABLED = booleanPreferencesKey("alarm_enabled")
    }

    override fun getAlarm(): Flow<Alarm> {
        return dataStore.data.map { preferences ->
            val time = preferences[PreferencesKeys.ALARM_TIME] ?: "07:00"
            val name = preferences[PreferencesKeys.ALARM_NAME] ?: "Despertar"
            val days = preferences[PreferencesKeys.ALARM_DAYS]?.toList() ?: listOf("L", "M", "X", "J", "V")
            val sound = preferences[PreferencesKeys.ALARM_SOUND] ?: "Mañana"
            val isEnabled = preferences[PreferencesKeys.ALARM_ENABLED] ?: false

            Alarm(
                time = time,
                name = name,
                days = days,
                sound = sound,
                isEnabled = isEnabled
            )
        }
    }

    override suspend fun saveAlarm(alarm: Alarm) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.ALARM_TIME] = alarm.time
            preferences[PreferencesKeys.ALARM_NAME] = alarm.name
            preferences[PreferencesKeys.ALARM_DAYS] = alarm.days.toSet()
            preferences[PreferencesKeys.ALARM_SOUND] = alarm.sound
            preferences[PreferencesKeys.ALARM_ENABLED] = alarm.isEnabled
        }
    }
}
