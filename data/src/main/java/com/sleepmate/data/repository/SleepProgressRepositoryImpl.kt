package com.sleepmate.data.repository

import com.sleepmate.data.datasource.local.SleepProgressDataStore
import com.sleepmate.domain.model.DailySleepProgress
import com.sleepmate.domain.repository.SleepProgressRepository
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SleepProgressRepositoryImpl @Inject constructor(
    private val sleepProgressDataStore: SleepProgressDataStore
) : SleepProgressRepository {
    
    override suspend fun getDailyProgress(date: LocalDate): DailySleepProgress? {
        return sleepProgressDataStore.getDailyProgress(date)
    }
    
    override suspend fun saveDailyProgress(progress: DailySleepProgress) {
        sleepProgressDataStore.saveDailyProgress(progress)
    }
    
    override suspend fun getProgressForDateRange(
        startDate: LocalDate, 
        endDate: LocalDate
    ): Map<LocalDate, DailySleepProgress> {
        return sleepProgressDataStore.getProgressForDateRange(startDate, endDate)
    }
}