package com.sleepmate.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sleepmate.data.datasource.local.entity.SleepHistoryEntity
import kotlinx.coroutines.flow.Flow
import java.time.Instant

@Dao
interface SleepHistoryDao {
    @Query("SELECT * FROM sleep_history ORDER BY startTime DESC")
    fun getAllSleepHistory(): Flow<List<SleepHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(history: List<SleepHistoryEntity>)

    @Query("SELECT * FROM sleep_history WHERE startTime >= :since ORDER BY startTime DESC")
    suspend fun getRecentHistory(since: Instant): List<SleepHistoryEntity>

    @Query("DELETE FROM sleep_history")
    suspend fun clearAll()
}
