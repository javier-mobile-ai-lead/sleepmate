package com.sleepmate.domain.repository

import com.sleepmate.domain.model.SleepHistory
import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface SleepHistoryRepository {
    fun getAllSleepHistory(): Flow<List<SleepHistory>>
    suspend fun saveSleepHistory(history: List<SleepHistory>)
    suspend fun getRecentHistory(since: Instant): List<SleepHistory>
    suspend fun clearAll()
}
