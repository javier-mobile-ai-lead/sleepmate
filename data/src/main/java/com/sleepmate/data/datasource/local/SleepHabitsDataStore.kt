package com.sleepmate.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sleepmate.domain.model.SleepHabit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sleep_habits_preferences")

class SleepHabitsDataStore @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {
    companion object {
        private val SLEEP_HABITS_KEY = stringPreferencesKey("sleep_habits")
        private val LAST_RESET_DATE_KEY = stringPreferencesKey("last_habit_reset_date")
    }
    
    fun getSleepHabits(): Flow<List<SleepHabit>> {
        return context.dataStore.data.map { preferences ->
            val habitsJson = preferences[SLEEP_HABITS_KEY] ?: "[]"
            try {
                val type = object : TypeToken<List<SleepHabit>>() {}.type
                gson.fromJson(habitsJson, type) ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }
    
    suspend fun saveHabits(habits: List<SleepHabit>) {
        context.dataStore.edit { preferences ->
            val habitsJson = gson.toJson(habits)
            preferences[SLEEP_HABITS_KEY] = habitsJson
        }
    }
    
    suspend fun addHabit(habit: SleepHabit) {
        context.dataStore.edit { preferences ->
            val currentHabitsJson = preferences[SLEEP_HABITS_KEY] ?: "[]"
            val type = object : TypeToken<List<SleepHabit>>() {}.type
            val currentHabits = try {
                gson.fromJson<List<SleepHabit>>(currentHabitsJson, type) ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
            
            val updatedHabits = currentHabits.toMutableList().apply { add(habit) }
            preferences[SLEEP_HABITS_KEY] = gson.toJson(updatedHabits)
        }
    }
    
    suspend fun updateHabit(updatedHabit: SleepHabit) {
        context.dataStore.edit { preferences ->
            val currentHabitsJson = preferences[SLEEP_HABITS_KEY] ?: "[]"
            val type = object : TypeToken<List<SleepHabit>>() {}.type
            val currentHabits = try {
                gson.fromJson<List<SleepHabit>>(currentHabitsJson, type) ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
            
            val updatedHabits = currentHabits.map { habit ->
                if (habit.id == updatedHabit.id) updatedHabit else habit
            }
            preferences[SLEEP_HABITS_KEY] = gson.toJson(updatedHabits)
        }
    }
    
    suspend fun deleteHabit(habitId: String) {
        context.dataStore.edit { preferences ->
            val currentHabitsJson = preferences[SLEEP_HABITS_KEY] ?: "[]"
            val type = object : TypeToken<List<SleepHabit>>() {}.type
            val currentHabits = try {
                gson.fromJson<List<SleepHabit>>(currentHabitsJson, type) ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
            
            val updatedHabits = currentHabits.filter { it.id != habitId }
            preferences[SLEEP_HABITS_KEY] = gson.toJson(updatedHabits)
        }
    }


    // 2. Función para obtener la última fecha registrada
    fun getLastResetDate(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[LAST_RESET_DATE_KEY]
        }
    }
    // 3. Función para guardar la fecha de hoy
    suspend fun saveLastResetDate(date: String) {
        context.dataStore.edit { preferences ->
            preferences[LAST_RESET_DATE_KEY] = date


}
}

// 4. Lógica para desmarcar todos los hábitos (JSON Read -> Modify -> Write)
suspend fun uncheckAllHabits() {
    context.dataStore.edit { preferences ->
        val currentHabitsJson = preferences[SLEEP_HABITS_KEY] ?: "[]"
        val type = object : TypeToken<List<SleepHabit>>() {}.type

        val currentHabits = try {
            gson.fromJson<List<SleepHabit>>(currentHabitsJson, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }

        // Si hay hábitos, creamos una nueva lista con isCompleted = false
        if (currentHabits.isNotEmpty()) {
            // Usamos .map para crear copias modificadas
            val resetHabits = currentHabits.map { habit ->
                habit.copy(isCompleted = false) // Asumiendo que es un data class
            }
            preferences[SLEEP_HABITS_KEY] = gson.toJson(resetHabits)
        }
    }
}
}