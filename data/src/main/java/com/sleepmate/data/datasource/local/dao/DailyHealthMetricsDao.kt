package com.sleepmate.data.datasource.local.dao

import androidx.room.*
import com.sleepmate.data.datasource.local.entity.DailyHealthMetricsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyHealthMetricsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(metrics: DailyHealthMetricsEntity)

    @Query("SELECT * FROM daily_health_metrics WHERE date = :date")
    suspend fun getMetricsForDate(date: String): DailyHealthMetricsEntity?

    @Query("SELECT * FROM daily_health_metrics ORDER BY date DESC LIMIT :limit")
    fun getRecentMetrics(limit: Int): Flow<List<DailyHealthMetricsEntity>>

    @Query("SELECT * FROM daily_health_metrics WHERE date BETWEEN :startDate AND :endDate ORDER BY date ASC")
    suspend fun getMetricsInRange(startDate: String, endDate: String): List<DailyHealthMetricsEntity>
}
