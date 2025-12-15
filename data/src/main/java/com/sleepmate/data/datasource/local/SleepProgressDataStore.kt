package com.sleepmate.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.sleepmate.domain.model.DailySleepProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

private val Context.sleepProgressDataStore: DataStore<Preferences> by preferencesDataStore(name = "sleep_progress_preferences")

@Singleton
class SleepProgressDataStore @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    
    suspend fun getDailyProgress(date: LocalDate): DailySleepProgress? {
        val key = stringPreferencesKey("progress_${date.format(dateFormatter)}")
        val preferences = context.sleepProgressDataStore.data.first()
        val progressJson = preferences[key] ?: return null
        
        return try {
            gson.fromJson(progressJson, DailySleepProgress::class.java)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun saveDailyProgress(progress: DailySleepProgress) {
        val key = stringPreferencesKey("progress_${progress.date.format(dateFormatter)}")
        context.sleepProgressDataStore.edit { preferences ->
            preferences[key] = gson.toJson(progress)
        }
    }
    
    fun getProgressForDateRange(
        startDate: LocalDate, 
        endDate: LocalDate
    ): Flow<Map<LocalDate, DailySleepProgress>> {
        return context.sleepProgressDataStore.data.map { preferences ->
            val progressMap = mutableMapOf<LocalDate, DailySleepProgress>()
            
            var currentDate = startDate
            while (!currentDate.isAfter(endDate)) {
                val key = stringPreferencesKey("progress_${currentDate.format(dateFormatter)}")
                val progressJson = preferences[key]
                
                if (progressJson != null) {
                    try {
                        val progress = gson.fromJson(progressJson, DailySleepProgress::class.java)
                        progressMap[currentDate] = progress
                    } catch (e: Exception) {
                        // Skip invalid entries
                    }
                }
                
                currentDate = currentDate.plusDays(1)
            }
            
            progressMap
        }
    }
}