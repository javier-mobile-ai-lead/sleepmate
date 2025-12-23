package com.sleepmate.domain.usecase

import com.sleepmate.domain.datasource.TrackerDataSource
import com.sleepmate.domain.model.DailySleepProgress
import com.sleepmate.domain.repository.SleepHabitRepository // 1. Importar esto
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.first

@Singleton
class UserProgressTracker @Inject constructor(
    private val trackerDataSource: TrackerDataSource,
    private val sleepHabitRepository: SleepHabitRepository // 2. Inyectar el repositorio
) {

    suspend fun trackAndResetIfNeeded() {
        val today = LocalDate.now()
        val lastResetDate = trackerDataSource.getLastResetDate()

        // Si ya reseteamos hoy, no hacemos nada
        if (lastResetDate == today) {
            return
        }

        // --- LÓGICA DE PROGRESO Y RACHAS EXISTENTE ---
        // (Tu código original de rachas se queda igual...)
        val yesterday = today.minusDays(1)
        val yesterdayProgress = trackerDataSource.getDailyProgress(yesterday)

        if (yesterdayProgress != null && isProgressCompleted(yesterdayProgress)) {
            val currentStreak = trackerDataSource.getStreakCount().first()
            trackerDataSource.saveStreakCount(currentStreak + 1)
        } else if (yesterdayProgress != null) {
            trackerDataSource.resetStreak()
        }

        // --- LÓGICA DE PROGRESO DIARIO EXISTENTE ---
        // (Esto crea el registro del nuevo día en el historial)
        val defaultHabits = trackerDataSource.getDefaultHabits()
        val todayProgress = DailySleepProgress(
            date = today,
            sleepTimerCompleted = false,
            habits = defaultHabits
        )
        trackerDataSource.saveDailyProgress(todayProgress)



        // Actualizamos la fecha de último reset
        trackerDataSource.setLastResetDate(today)
    }

    private fun isProgressCompleted(progress: DailySleepProgress): Boolean {
        return progress.sleepTimerCompleted && progress.habits.all { it.isCompleted }
    }
}
