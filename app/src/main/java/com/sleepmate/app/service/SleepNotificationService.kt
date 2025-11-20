package com.sleepmate.app.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.sleepmate.app.MainActivity
import com.sleepmate.domain.usecase.GetEnableNotificationPushUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SleepNotificationService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getEnableNotificationPushUseCase: GetEnableNotificationPushUseCase
) {
    companion object {
        private const val SLEEP_NOTIFICATION_CHANNEL_ID = "sleep_mode_channel"
        private const val SLEEP_NOTIFICATION_CHANNEL_NAME = "Sleep Mode Notifications"
        private const val SLEEP_NOTIFICATION_ID = 2001
    }
    
    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
    
    init {
        createNotificationChannel()
    }
    
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                SLEEP_NOTIFICATION_CHANNEL_ID,
                SLEEP_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notificaciones del modo sueño de SleepMate"
                enableVibration(true)
                setShowBadge(true)
            }
            
            notificationManager.createNotificationChannel(channel)
            Timber.d("Sleep notification channel created")
        }
    }
    
    fun sendSleepNotification() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val isNotificationEnabled = getEnableNotificationPushUseCase.isNotificationPushEnabled().first()
                
                if (!isNotificationEnabled) {
                    Timber.d("Push notifications are disabled, skipping sleep notification")
                    return@launch
                }
                
                // Intent to open the app when notification is tapped
                val intent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                
                val pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                
                // Build the notification
                val notification = NotificationCompat.Builder(context, SLEEP_NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_lock_idle_lock) // You can replace with your custom icon
                    .setContentTitle("SleepMate")
                    .setContentText("Modo Sueño activado. Descansa bien 🌙")
                    .setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText("Modo Sueño activado. Descansa bien 🌙")
                    )
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_REMINDER)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setVibrate(longArrayOf(0, 500, 1000))
                    .setDefaults(NotificationCompat.DEFAULT_SOUND)
                    .build()
                
                notificationManager.notify(SLEEP_NOTIFICATION_ID, notification)
                Timber.d("Sleep notification sent successfully")
                
            } catch (e: Exception) {
                Timber.e(e, "Failed to send sleep notification")
            }
        }
    }
    
    fun cancelSleepNotification() {
        notificationManager.cancel(SLEEP_NOTIFICATION_ID)
        Timber.d("Sleep notification cancelled")
    }
}