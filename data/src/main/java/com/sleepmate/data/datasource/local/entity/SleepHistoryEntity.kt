package com.sleepmate.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "sleep_history")
data class SleepHistoryEntity(
    @PrimaryKey val id: String, // Usaremos el ID de Health Connect para evitar duplicados
    val startTime: Instant,
    val endTime: Instant,
    val durationMinutes: Long,
    val source: String? = null
)
