package com.sleepmate.domain.model

import java.time.LocalDateTime

data class SleepTimer(
    val isActive: Boolean = false,
    val startTime: LocalDateTime? = null,
    val endTime: LocalDateTime? = null,
    val durationMinutes: Int = 25
)

data class SleepModeState(
    val isActivated: Boolean = false,
    val finishTimestamp: String? = null // ISO 8601 format
)