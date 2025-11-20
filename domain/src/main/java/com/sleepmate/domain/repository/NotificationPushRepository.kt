package com.sleepmate.domain.repository

import kotlinx.coroutines.flow.Flow

interface NotificationPushRepository {
    fun isNotificationPushEnabled(): Flow<Boolean>
    suspend fun setNotificationPushEnabled(enabled: Boolean)
}