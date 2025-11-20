package com.sleepmate.data.repository

import com.sleepmate.domain.model.SleepHabit
import com.sleepmate.domain.repository.SleepHabitRepository
import com.sleepmate.data.datasource.local.SleepHabitLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class SleepHabitRepositoryImpl @Inject constructor(
    private val localDataSource: SleepHabitLocalDataSource
) : SleepHabitRepository {
    
    override fun getSleepHabits(): Flow<List<SleepHabit>> {
        return localDataSource.getSleepHabits()
    }
    
    override suspend fun addSleepHabit(habit: SleepHabit) {
        localDataSource.addSleepHabit(habit)
    }
    
    override suspend fun updateSleepHabit(habit: SleepHabit) {
        localDataSource.updateSleepHabit(habit)
    }
    
    override suspend fun deleteSleepHabit(habitId: String) {
        localDataSource.deleteSleepHabit(habitId)
    }
    
    override suspend fun getSleepHabitById(habitId: String): SleepHabit? {
        return localDataSource.getSleepHabitById(habitId)
    }
}