package com.sleepmate.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sleepmate.data.datasource.local.dao.DailyHealthMetricsDao
import com.sleepmate.data.datasource.local.dao.SleepHistoryDao
import com.sleepmate.data.datasource.local.entity.DailyHealthMetricsEntity
import com.sleepmate.data.datasource.local.entity.SleepHistoryEntity

@Database(
    entities = [SleepHistoryEntity::class, DailyHealthMetricsEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SleepMateDatabase : RoomDatabase() {
    abstract fun sleepHistoryDao(): SleepHistoryDao
    abstract fun dailyHealthMetricsDao(): DailyHealthMetricsDao
}
