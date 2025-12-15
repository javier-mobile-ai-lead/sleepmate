package com.sleepmate.domain.repository

import com.sleepmate.domain.model.DailySleepProgress
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface SleepProgressRepository {
    suspend fun getDailyProgress(date: LocalDate): DailySleepProgress?
    suspend fun saveDailyProgress(progress: DailySleepProgress)
    fun getProgressForDateRange(startDate: LocalDate, endDate: LocalDate): Flow<Map<LocalDate, DailySleepProgress>>
}