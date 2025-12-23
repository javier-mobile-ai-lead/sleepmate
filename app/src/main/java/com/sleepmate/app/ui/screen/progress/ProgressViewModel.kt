package com.sleepmate.app.ui.screen.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.domain.datasource.TrackerDataSource
import com.sleepmate.domain.model.DailySleepProgress
import com.sleepmate.domain.repository.SleepHabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(
    trackerDataSource: TrackerDataSource,
    sleepHabitRepository: SleepHabitRepository
) : ViewModel() {

    // CAMBIO 1: Eliminamos 'private val today = LocalDate.now()' porque era estático.
    // Usamos LocalDate.now() directamente donde se necesite para que sea dinámico.

    // Fetch a year of progress to calculate the streak accurately.
    private val startDate = LocalDate.now().minusYears(1)

    // A flow that contains all progress data for the last year.
    // Usamos LocalDate.now() aquí para el límite inicial de la consulta.
    private val historicalProgress: StateFlow<Map<LocalDate, DailySleepProgress>> =
        trackerDataSource.getProgressForDateRange(startDate, LocalDate.now())
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyMap()
            )

    // Calculate the current streak based on the historical progress.
    val currentStreak: StateFlow<Int> = historicalProgress.map { progressMap ->
        calculateStreak(progressMap)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    // The progress for the current week, for the UI.
    val progressByDay: StateFlow<Map<LocalDate, DailySleepProgress>> = historicalProgress.map { historical ->
        // CAMBIO 2: Obtenemos la fecha fresca dentro del map
        val currentToday = LocalDate.now()
        val startOfWeek = currentToday.with(DayOfWeek.MONDAY)
        val endOfWeek = currentToday.with(DayOfWeek.SUNDAY)
        historical.filterKeys { it in startOfWeek..endOfWeek }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyMap()
    )

    val isLoading: StateFlow<Boolean> = historicalProgress.map { it.isEmpty() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = true
        )

    val weekSummary: StateFlow<WeekSummary> = combine(
        progressByDay, // Use the derived weekly progress
        sleepHabitRepository.getSleepHabits()
    ) { progressMap, currentHabits ->
        // CAMBIO 3: Obtenemos la fecha fresca dentro del combine
        val currentToday = LocalDate.now()
        val startOfWeek = currentToday.with(DayOfWeek.MONDAY)
        val endOfWeek = currentToday.with(DayOfWeek.SUNDAY)

        var completedTimers = 0
        var daysWithProgress = 0

        var currentDate = startOfWeek
        while (!currentDate.isAfter(endOfWeek)) {
            val dayProgress = progressMap[currentDate]
            if (dayProgress != null) {
                daysWithProgress++
                if (dayProgress.habits.isNotEmpty() && dayProgress.habits.all { it.isCompleted }) {
                    completedTimers++
                }
            }
            currentDate = currentDate.plusDays(1)
        }

        val totalHabits = currentHabits.size
        // Esto calculará correctamente sobre la lista vacía cuando el repo se actualice
        val habitsCompletedToday = currentHabits.count { it.isCompleted }

        WeekSummary(
            completedTimers = completedTimers,
            totalTimers = 7,
            completedHabits = habitsCompletedToday,
            totalHabits = totalHabits,
            daysWithProgress = daysWithProgress
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = WeekSummary(0, 7, 0, 0, 0)
    )

    private fun calculateStreak(progressMap: Map<LocalDate, DailySleepProgress>): Int {
        var streak = 0
        var currentDate = LocalDate.now() // Esto ya estaba bien, siempre usa la fecha actual

        val todayProgress = progressMap[currentDate]
        val todayCompleted = todayProgress != null && todayProgress.habits.isNotEmpty() && todayProgress.habits.all { it.isCompleted }

        // If today's habits are not all complete, the streak is calculated up to yesterday.
        if (!todayCompleted) {
            currentDate = currentDate.minusDays(1)
        }

        // Now, iterate backwards from `currentDate`
        while (true) {
            val dayProgress = progressMap[currentDate]
            if (dayProgress != null && dayProgress.habits.isNotEmpty() && dayProgress.habits.all { it.isCompleted }) {
                streak++
                currentDate = currentDate.minusDays(1)
            } else {
                break // Streak is broken
            }
        }
        return streak
    }

    fun getCurrentWeekDates(): List<LocalDate> {
        // CAMBIO 4: Usamos LocalDate.now() aquí también
        val currentToday = LocalDate.now()
        val startOfWeek = currentToday.with(DayOfWeek.MONDAY)
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
