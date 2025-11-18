package com.sleepmate.app.service

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.provider.Settings
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DoNotDisturbManager @Inject constructor(
    private val context: Context
) {
    
    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
    
    /**
     * Checks if the app has permission to access notification policy (DND settings)
     */
    fun hasNotificationPolicyAccess(): Boolean {
        return notificationManager.isNotificationPolicyAccessGranted
    }
    
    /**
     * Opens the system settings for notification policy access
     */
    fun openNotificationPolicySettings() {
        try {
            val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
            Timber.d("Opened notification policy access settings")
        } catch (e: Exception) {
            Timber.e(e, "Failed to open notification policy access settings")
        }
    }
    
    /**
     * Activates Do Not Disturb mode if permission is granted
     * @return true if successfully activated, false if permission not granted
     */
    fun activateDoNotDisturb(): Boolean {
        return try {
            if (hasNotificationPolicyAccess()) {
                // Set interruption filter to NONE (complete silence)
                notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE)
                Timber.d("Do Not Disturb mode activated successfully")
                true
            } else {
                Timber.w("Cannot activate Do Not Disturb - permission not granted")
                false
            }
        } catch (e: Exception) {
            Timber.e(e, "Failed to activate Do Not Disturb mode")
            false
        }
    }
    
    /**
     * Deactivates Do Not Disturb mode (restores normal interruption filter)
     * @return true if successfully deactivated, false if permission not granted
     */
    fun deactivateDoNotDisturb(): Boolean {
        return try {
            if (hasNotificationPolicyAccess()) {
                // Restore normal interruption filter
                notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
                Timber.d("Do Not Disturb mode deactivated successfully")
                true
            } else {
                Timber.w("Cannot deactivate Do Not Disturb - permission not granted")
                false
            }
        } catch (e: Exception) {
            Timber.e(e, "Failed to deactivate Do Not Disturb mode")
            false
        }
    }
    
    /**
     * Gets the current interruption filter status
     */
    fun getCurrentInterruptionFilter(): Int {
        return notificationManager.currentInterruptionFilter
    }
    
    /**
     * Checks if Do Not Disturb is currently active
     */
    fun isDoNotDisturbActive(): Boolean {
        return notificationManager.currentInterruptionFilter == NotificationManager.INTERRUPTION_FILTER_NONE
    }
}