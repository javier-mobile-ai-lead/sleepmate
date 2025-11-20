package com.sleepmate.domain.datasource

import com.sleepmate.domain.model.DailySleepProgress
import com.sleepmate.domain.model.SleepHabit
import java.time.LocalDate

interface TrackerDataSource {
    suspend fun getDailyProgress(date: LocalDate): DailySleepProgress?
    suspend fun saveDailyProgress(progress: DailySleepProgress)
    suspend fun getDefaultHabits(): List<SleepHabit>

    suspend fun getStreakCount(): Int
    suspend fun saveStreakCount(count: Int)
    suspend fun resetStreak()

    suspend fun getLastResetDate(): LocalDate?
    suspend fun setLastResetDate(date: LocalDate)
    
    suspend fun getProgressForDateRange(startDate: LocalDate, endDate: LocalDate): Map<LocalDate, DailySleepProgress>
}