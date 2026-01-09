package com.sleepmate.data.repository

import com.sleepmate.data.datasource.local.dao.SleepHistoryDao
import com.sleepmate.data.datasource.local.entity.SleepHistoryEntity
import com.sleepmate.domain.model.SleepHistory
import com.sleepmate.domain.repository.SleepHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SleepHistoryRepositoryImpl @Inject constructor(
    private val sleepHistoryDao: SleepHistoryDao
) : SleepHistoryRepository {

    override fun getAllSleepHistory(): Flow<List<SleepHistory>> {
        return sleepHistoryDao.getAllSleepHistory().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveSleepHistory(history: List<SleepHistory>) {
        sleepHistoryDao.insertAll(history.map { it.toEntity() })
    }

    override suspend fun getRecentHistory(since: Instant): List<SleepHistory> {
        return sleepHistoryDao.getRecentHistory(since).map { it.toDomain() }
    }

    override suspend fun clearAll() {
        sleepHistoryDao.clearAll()
    }

    private fun SleepHistoryEntity.toDomain() = SleepHistory(
        id = id,
        startTime = startTime,
        endTime = endTime,
        durationMinutes = durationMinutes,
        source = source
    )

    private fun SleepHistory.toEntity() = SleepHistoryEntity(
        id = id,
        startTime = startTime,
        endTime = endTime,
        durationMinutes = durationMinutes,
        source = source
    )
}
