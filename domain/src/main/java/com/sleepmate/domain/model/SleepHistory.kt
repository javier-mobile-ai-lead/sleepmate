package com.sleepmate.domain.model

import java.time.Instant

data class SleepHistory(
    val id: String,
    val startTime: Instant,
    val endTime: Instant,
    val durationMinutes: Long,
    val source: String? = null
)
