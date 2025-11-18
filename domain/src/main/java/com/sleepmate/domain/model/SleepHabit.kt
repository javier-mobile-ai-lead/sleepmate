package com.sleepmate.domain.model

import java.util.UUID

data class SleepHabit(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val subtitle: String? = null,
    val isCompleted: Boolean = false,
    val isSuggested: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

data class SuggestedHabit(
    val title: String,
    val subtitle: String
)