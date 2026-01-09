package com.sleepmate.domain.usecase

import com.sleepmate.domain.model.SleepHistory
import com.sleepmate.domain.repository.HealthDataRepository
import com.sleepmate.domain.repository.SleepHistoryRepository
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject

/**
 * Use case to synchronize sleep history from Health Connect to local database.
 * Usually fetches the last 30 days of data.
 */
class SyncSleepHistoryUseCase @Inject constructor(
    private val healthDataRepository: HealthDataRepository,
    private val sleepHistoryRepository: SleepHistoryRepository
) {
    suspend operator fun invoke(days: Long = 30) {
        val endTime = Instant.now()
        val startTime = endTime.minus(days, ChronoUnit.DAYS)

        val sleepSessions = healthDataRepository.fetchSleepSessions(startTime, endTime)
        
        val historyList = sleepSessions.map { data ->
            SleepHistory(
                id = data.id,
                startTime = data.startTime,
                endTime = data.endTime,
                durationMinutes = data.durationMinutes,
                source = data.source
            )
        }

        if (historyList.isNotEmpty()) {
            sleepHistoryRepository.saveSleepHistory(historyList)
        }
    }
}
