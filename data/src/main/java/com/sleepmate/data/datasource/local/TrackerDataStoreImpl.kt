package com.sleepmate.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sleepmate.domain.datasource.TrackerDataSource
import com.sleepmate.domain.model.DailySleepProgress
import com.sleepmate.domain.model.SleepHabit
import com.sleepmate.domain.repository.SleepHabitRepository
import com.sleepmate.domain.repository.SleepProgressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

private val Context.trackerDataStore: DataStore<Preferences> by preferencesDataStore(name = "tracker_preferences")

@Singleton
class TrackerDataStoreImpl @Inject constructor(
    private val context: Context,
    private val sleepProgressRepository: SleepProgressRepository,
    private val sleepHabitRepository: SleepHabitRepository
) : TrackerDataSource {

    companion object {
        private val STREAK_COUNT_KEY = intPreferencesKey("streak_count")
        private val LAST_RESET_DATE_KEY = stringPreferencesKey("last_reset_date")
    }

    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    override suspend fun getDailyProgress(date: LocalDate): DailySleepProgress? {
        return sleepProgressRepository.getDailyProgress(date)
    }

    override suspend fun saveDailyProgress(progress: DailySleepProgress) {
        sleepProgressRepository.saveDailyProgress(progress)
    }

    override suspend fun getDefaultHabits(): List<SleepHabit> {
        return sleepHabitRepository.getSleepHabits().first()
    }

    override fun getStreakCount(): Flow<Int> {
        return context.trackerDataStore.data.map { preferences ->
            preferences[STREAK_COUNT_KEY] ?: 0
        }
    }

    override suspend fun saveStreakCount(count: Int) {
        context.trackerDataStore.edit { preferences ->
            preferences[STREAK_COUNT_KEY] = count
        }
    }

    override suspend fun resetStreak() {
        saveStreakCount(0)
    }

    override suspend fun getLastResetDate(): LocalDate? {
        val preferences = context.trackerDataStore.data.first()
        val dateString = preferences[LAST_RESET_DATE_KEY] ?: return null

        return try {
            LocalDate.parse(dateString, dateFormatter)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun setLastResetDate(date: LocalDate) {
        context.trackerDataStore.edit { preferences ->
            preferences[LAST_RESET_DATE_KEY] = date.format(dateFormatter)
        }
    }

    override fun getProgressForDateRange(
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<Map<LocalDate, DailySleepProgress>> {
        return sleepProgressRepository.getProgressForDateRange(startDate, endDate)
    }
}