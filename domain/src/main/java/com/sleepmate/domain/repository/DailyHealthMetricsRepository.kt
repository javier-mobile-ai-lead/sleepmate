package com.sleepmate.domain.repository

import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface DailyHealthMetricsRepository {
    fun getRecentMetrics(limit: Int): Flow<List<DailyHealthMetrics>>
    suspend fun saveMetrics(metrics: DailyHealthMetrics)
    suspend fun getMetricsForDate(date: String): DailyHealthMetrics?
    suspend fun getMetricsInRange(startDate: String, endDate: String): List<DailyHealthMetrics>
}
