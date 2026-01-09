package com.sleepmate.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "daily_health_metrics")
data class DailyHealthMetricsEntity(
    @PrimaryKey val date: String, // Format "yyyy-MM-dd"
    val sleepDurationMinutes: Long,
    val steps: Long,
    val caloriesBurned: Long?,
    val avgHeartRate: Long?,
    val restingHeartRate: Long?,
    val avgOxygenSaturation: Double?,
    val hrvRmssd: Double?,
    val weight: Double?,
    val height: Double?,
    val sourceApp: String?, // Added to identify the origin of the data
    val lastSyncTime: Instant
)
