package com.sleepmate.domain.usecase

import com.sleepmate.domain.repository.NotificationPushRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEnableNotificationPushUseCase @Inject constructor(
    private val notificationPushRepository: NotificationPushRepository
) {
    fun isNotificationPushEnabled(): Flow<Boolean> = notificationPushRepository.isNotificationPushEnabled()
    
    suspend fun setNotificationPushEnabled(enabled: Boolean) {
        notificationPushRepository.setNotificationPushEnabled(enabled)
    }
}