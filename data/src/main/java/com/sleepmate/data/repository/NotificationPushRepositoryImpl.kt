package com.sleepmate.data.repository

import com.sleepmate.data.datasource.local.NotificationPushPreferences
import com.sleepmate.domain.repository.NotificationPushRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationPushRepositoryImpl @Inject constructor(
    private val notificationPushPreferences: NotificationPushPreferences
) : NotificationPushRepository {
    
    override fun isNotificationPushEnabled(): Flow<Boolean> {
        return notificationPushPreferences.isNotificationPushEnabled
    }
    
    override suspend fun setNotificationPushEnabled(enabled: Boolean) {
        notificationPushPreferences.setNotificationPushEnabled(enabled)
    }
}