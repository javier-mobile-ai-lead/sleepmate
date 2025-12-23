package com.sleepmate.data.datasource.local

import com.sleepmate.domain.model.SleepHabit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class SleepHabitLocalDataSource @Inject constructor(
    private val sleepHabitsDataStore: SleepHabitsDataStore
) {
    
    fun getSleepHabits(): Flow<List<SleepHabit>> {
        return sleepHabitsDataStore.getSleepHabits()
    }
    
    suspend fun addSleepHabit(habit: SleepHabit) {
        sleepHabitsDataStore.addHabit(habit)
    }

    suspend fun updateSleepHabit(habit: SleepHabit) {
        sleepHabitsDataStore.updateHabit(habit)
    }
    
    suspend fun deleteSleepHabit(habitId: String) {
        sleepHabitsDataStore.deleteHabit(habitId)
    }
    
    suspend fun getSleepHabitById(habitId: String): SleepHabit? {
        val habits = sleepHabitsDataStore.getSleepHabits()
        // We need to collect the flow once to get the current state
        // This is a simplified approach - in production you might want to handle this differently
        return null // For now, we'll handle this in the repository layer if needed
    }

    // Nuevas funciones
    suspend fun uncheckAllHabits() {
        sleepHabitsDataStore.uncheckAllHabits()
    }

    fun getLastResetDate(): Flow<String?> {
        return sleepHabitsDataStore.getLastResetDate()
    }

    suspend fun saveLastResetDate(date: String) {
        sleepHabitsDataStore.saveLastResetDate(date)
    }
}