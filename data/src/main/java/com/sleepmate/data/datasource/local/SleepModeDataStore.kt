package com.sleepmate.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sleepmate.domain.model.SleepModeState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sleep_mode_preferences")

class SleepModeDataStore @Inject constructor(
    private val context: Context
) {
    companion object {
        private val MODO_SUENO_ACTIVADO = booleanPreferencesKey("modo_sueno_activado")
        private val TIEMPO_FIN_TEMPORIZADOR = stringPreferencesKey("tiempoFinTemporizador")
    }
    
    fun getSleepModeState(): Flow<SleepModeState> {
        return context.dataStore.data.map { preferences ->
            SleepModeState(
                isActivated = preferences[MODO_SUENO_ACTIVADO] ?: false,
                finishTimestamp = preferences[TIEMPO_FIN_TEMPORIZADOR]
            )
        }
    }
    
    suspend fun setSleepModeActivated(isActivated: Boolean, finishTimestamp: String? = null) {
        context.dataStore.edit { preferences ->
            preferences[MODO_SUENO_ACTIVADO] = isActivated
            if (finishTimestamp != null) {
                preferences[TIEMPO_FIN_TEMPORIZADOR] = finishTimestamp
            } else {
                preferences.remove(TIEMPO_FIN_TEMPORIZADOR)
            }
        }
    }
}