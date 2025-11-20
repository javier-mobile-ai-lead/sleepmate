package com.sleepmate.domain.model

import java.time.LocalDate

data class DailySleepProgress(
    val date: LocalDate,
    val sleepTimerCompleted: Boolean = false,
    val habits: List<SleepHabit> = emptyList()
)