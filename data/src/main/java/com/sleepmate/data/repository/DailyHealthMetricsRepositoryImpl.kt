package com.sleepmate.data.repository

import com.sleepmate.data.datasource.local.dao.DailyHealthMetricsDao
import com.sleepmate.data.datasource.local.entity.DailyHealthMetricsEntity
import com.sleepmate.domain.repository.DailyHealthMetrics
import com.sleepmate.domain.repository.DailyHealthMetricsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import javax.inject.Inject

class DailyHealthMetricsRepositoryImpl @Inject constructor(
    private val dao: DailyHealthMetricsDao
) : DailyHealthMetricsRepository {

    override fun getRecentMetrics(limit: Int): Flow<List<DailyHealthMetrics>> {
        return dao.getRecentMetrics(limit).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveMetrics(metrics: DailyHealthMetrics) {
        dao.insert(metrics.toEntity())
    }

    override suspend fun getMetricsForDate(date: String): DailyHealthMetrics? {
        return dao.getMetricsForDate(date)?.toDomain()
    }

    override suspend fun getMetricsInRange(startDate: String, endDate: String): List<DailyHealthMetrics> {
        return dao.getMetricsInRange(startDate, endDate).map { it.toDomain() }
    }

    private fun DailyHealthMetricsEntity.toDomain() = DailyHealthMetrics(
        date = date,
        sleepDurationMinutes = sleepDurationMinutes,
        steps = steps,
        caloriesBurned = caloriesBurned,
        avgHeartRate = avgHeartRate,
        restingHeartRate = restingHeartRate,
        avgOxygenSaturation = avgOxygenSaturation,
        hrvRmssd = hrvRmssd,
        weight = weight,
        height = height,
        sourceApp = sourceApp,
        lastSyncTime = lastSyncTime
    )

    private fun DailyHealthMetrics.toEntity() = DailyHealthMetricsEntity(
        date = date,
        sleepDurationMinutes = sleepDurationMinutes,
        steps = steps,
        caloriesBurned = caloriesBurned,
        avgHeartRate = avgHeartRate,
        restingHeartRate = restingHeartRate,
        avgOxygenSaturation = avgOxygenSaturation,
        hrvRmssd = hrvRmssd,
        weight = weight,
        height = height,
        sourceApp = sourceApp,
        lastSyncTime = lastSyncTime
    )
}
