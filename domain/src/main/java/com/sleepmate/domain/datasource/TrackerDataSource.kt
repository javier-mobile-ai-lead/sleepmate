package com.sleepmate.domain.datasource

import com.sleepmate.domain.model.DailySleepProgress
import com.sleepmate.domain.model.SleepHabit
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerDataSource {
    suspend fun getDailyProgress(date: LocalDate): DailySleepProgress?
    suspend fun saveDailyProgress(progress: DailySleepProgress)
    suspend fun getDefaultHabits(): List<SleepHabit>

    fun getStreakCount(): Flow<Int>
    suspend fun saveStreakCount(count: Int)
    suspend fun resetStreak()

    suspend fun getLastResetDate(): LocalDate?
    suspend fun setLastResetDate(date: LocalDate)
    
    fun getProgressForDateRange(startDate: LocalDate, endDate: LocalDate): Flow<Map<LocalDate, DailySleepProgress>>
}