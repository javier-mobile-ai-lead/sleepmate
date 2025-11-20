package com.sleepmate.domain.usecase

import com.sleepmate.domain.datasource.TrackerDataSource
import com.sleepmate.domain.model.DailySleepProgress
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProgressTracker @Inject constructor(
    private val trackerDataSource: TrackerDataSource
) {
    
    suspend fun trackAndResetIfNeeded() {
        val today = LocalDate.now()
        val lastResetDate = trackerDataSource.getLastResetDate()
        
        // Check if we already reset today
        if (lastResetDate == today) {
            return
        }
        
        // Check yesterday's progress if it exists
        val yesterday = today.minusDays(1)
        val yesterdayProgress = trackerDataSource.getDailyProgress(yesterday)
        
        // Update streak based on yesterday's completion
        if (yesterdayProgress != null && isProgressCompleted(yesterdayProgress)) {
            // User completed everything yesterday, increment streak
            val currentStreak = trackerDataSource.getStreakCount()
            trackerDataSource.saveStreakCount(currentStreak + 1)
        } else if (yesterdayProgress != null) {
            // Progress exists but not completed, reset streak
            trackerDataSource.resetStreak()
        }
        // If no progress for yesterday, don't change streak (maybe first time using app)
        
        // Create today's progress with default habits
        val defaultHabits = trackerDataSource.getDefaultHabits()
        val todayProgress = DailySleepProgress(
            date = today,
            sleepTimerCompleted = false,
            habits = defaultHabits
        )
        
        // Save today's progress
        trackerDataSource.saveDailyProgress(todayProgress)
        
        // Update last reset date
        trackerDataSource.setLastResetDate(today)
    }
    
    private fun isProgressCompleted(progress: DailySleepProgress): Boolean {
        return progress.sleepTimerCompleted && progress.habits.all { it.isCompleted }
    }
}