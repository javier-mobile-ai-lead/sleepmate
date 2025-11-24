package com.sleepmate.app.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.sleepmate.app.MainActivity
import com.sleepmate.app.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class SleepTimerService : LifecycleService() {

    @Inject
    lateinit var sleepTimerManager: SleepTimerManager

    private var serviceJob: Job? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        serviceJob?.cancel()
        serviceJob = lifecycleScope.launch {
            sleepTimerManager.timerState.collectLatest { state ->
                if (state is TimerState.Active) {
                    // Start foreground with an initial notification
                    startForeground(NOTIFICATION_ID, createNotification(this@SleepTimerService,"Calculating time...", isOngoing = true))

                    // This inner loop will be cancelled by collectLatest when state changes
                    while (true) {
                        val remainingMillis = state.endTimeMillis - System.currentTimeMillis()
                        if (remainingMillis <= 0) {
                            // The timer has finished, the Receiver will handle cleanup.
                            // The state will soon become Inactive, which will stop the service.
                            break
                        }
                        val remainingTime = formatMillisToTime(remainingMillis)
                        val updatedNotification = createNotification(this@SleepTimerService, "Tiempo restante: $remainingTime", isOngoing = true)
                        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.notify(NOTIFICATION_ID, updatedNotification)
                        delay(1000)
                    }
                } else { // Inactive state
                    stopSelf()
                }
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        serviceJob?.cancel()
        super.onDestroy()
    }

    companion object {
        const val NOTIFICATION_ID = 1988
        private const val NOTIFICATION_CHANNEL_ID = "sleep_timer_channel"

        fun formatMillisToTime(millis: Long): String {
            val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(minutes)
            return String.format("%02d:%02d", minutes, seconds)
        }

        fun createNotification(context: Context, contentText: String, isOngoing: Boolean): android.app.Notification {
            createNotificationChannel(context)

            val openAppIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                // Add the destination route as an extra
                putExtra("destination_route", "sleep_timer_screen")
            }
            val pendingIntent = PendingIntent.getActivity(
                context, 0, openAppIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val title = if (isOngoing) "Temporizador Activo" else "Temporizador Finalizado"

            return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(contentText)
                .setSmallIcon(R.drawable.ic_logo) // Make sure you have this drawable
                .setContentIntent(pendingIntent)
                .setOnlyAlertOnce(isOngoing)
                .setOngoing(isOngoing)
                .setAutoCancel(!isOngoing)
                .build()
        }

        fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "Sleep Timer",
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    description = "Notification for the active sleep timer"
                }
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }
}
