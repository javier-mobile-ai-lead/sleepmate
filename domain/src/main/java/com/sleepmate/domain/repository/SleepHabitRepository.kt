package com.sleepmate.domain.repository

import com.sleepmate.domain.model.SleepHabit
import kotlinx.coroutines.flow.Flow

interface SleepHabitRepository {
    fun getSleepHabits(): Flow<List<SleepHabit>>
    suspend fun addSleepHabit(habit: SleepHabit)
    suspend fun updateSleepHabit(habit: SleepHabit)
    suspend fun deleteSleepHabit(habitId: String)
    suspend fun getSleepHabitById(habitId: String): SleepHabit?

    suspend fun uncheckAllHabits()
    fun getLastResetDate(): Flow<String?>
    suspend fun saveLastResetDate(date: String)
}