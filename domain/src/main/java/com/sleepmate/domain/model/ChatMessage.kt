package com.sleepmate.domain.model

data class ChatMessage(
    val id: String,
    val content: String,
    val isFromUser: Boolean,
    val timestamp: Long,
    val isLoading: Boolean = false
)

data class AIUsageRecord(
    val installationId: String,
    val lastUsed: Long // timestamp in UTC
)