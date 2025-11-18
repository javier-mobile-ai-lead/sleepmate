package com.sleepmate.app.ui.screen.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.domain.datasource.TrackerDataSource
import com.sleepmate.domain.model.DailySleepProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val trackerDataSource: TrackerDataSource
) : ViewModel() {
    
    private val _currentStreak = MutableStateFlow(0)
    val currentStreak: StateFlow<Int> = _currentStreak.asStateFlow()
    
    private val _progressByDay = MutableStateFlow<Map<LocalDate, DailySleepProgress>>(emptyMap())
    val progressByDay: StateFlow<Map<LocalDate, DailySleepProgress>> = _progressByDay.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadData()
    }
    
    fun refreshData() {
        loadData()
    }
    
    private fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true
            
            try {
                // Load current streak
                _currentStreak.value = trackerDataSource.getStreakCount()
                
                // Load current week progress
                val today = LocalDate.now()
                val startOfWeek = today.with(DayOfWeek.MONDAY)
                val endOfWeek = today.with(DayOfWeek.SUNDAY)
                
                val weekProgress = trackerDataSource.getProgressForDateRange(startOfWeek, endOfWeek)
                _progressByDay.value = weekProgress
                
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun getWeekSummary(): WeekSummary {
        val progressMap = _progressByDay.value
        val today = LocalDate.now()
        val startOfWeek = today.with(DayOfWeek.MONDAY)
        val endOfWeek = today.with(DayOfWeek.SUNDAY)
        
        var completedTimers = 0
        var totalHabitsCompleted = 0
        var totalHabits = 0
        var daysWithProgress = 0
        
        var currentDate = startOfWeek
        while (!currentDate.isAfter(endOfWeek)) {
            val dayProgress = progressMap[currentDate]
            if (dayProgress != null) {
                daysWithProgress++
                if (dayProgress.sleepTimerCompleted) {
                    completedTimers++
                }
                totalHabitsCompleted += dayProgress.habits.count { it.isCompleted }
                totalHabits += dayProgress.habits.size
            }
            currentDate = currentDate.plusDays(1)
        }
        
        return WeekSummary(
            completedTimers = completedTimers,
            totalTimers = 7,
            completedHabits = totalHabitsCompleted,
            totalHabits = totalHabits,
            daysWithProgress = daysWithProgress
        )
    }
    
    fun getCurrentWeekDates(): List<LocalDate> {
        val today = LocalDate.now()
        val startOfWeek = today.with(DayOfWeek.MONDAY)
        
        return (0..6).map { startOfWeek.plusDays(it.toLong()) }
    }
    
    fun getDayOfWeekDisplay(date: LocalDate): String {
        return when (date.dayOfWeek) {
            DayOfWeek.MONDAY -> "L"
            DayOfWeek.TUESDAY -> "M"
            DayOfWeek.WEDNESDAY -> "X"
            DayOfWeek.THURSDAY -> "J"
            DayOfWeek.FRIDAY -> "V"
            DayOfWeek.SATURDAY -> "S"
            DayOfWeek.SUNDAY -> "D"
        }
    }
}

data class WeekSummary(
    val completedTimers: Int,
    val totalTimers: Int,
    val completedHabits: Int,
    val totalHabits: Int,
    val daysWithProgress: Int
)