package com.sleepmate.domain.repository

import com.sleepmate.domain.model.DailySleepProgress
import java.time.LocalDate

interface SleepProgressRepository {
    suspend fun getDailyProgress(date: LocalDate): DailySleepProgress?
    suspend fun saveDailyProgress(progress: DailySleepProgress)
    suspend fun getProgressForDateRange(startDate: LocalDate, endDate: LocalDate): Map<LocalDate, DailySleepProgress>
}