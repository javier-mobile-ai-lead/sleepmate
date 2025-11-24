package com.sleepmate.app.service

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sleepmate.app.service.DeviceAdminManager
import com.sleepmate.app.service.DoNotDisturbManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SleepTimerReceiver : BroadcastReceiver() {

    @Inject
    lateinit var sleepTimerManager: SleepTimerManager

    @Inject
    lateinit var doNotDisturbManager: DoNotDisturbManager

    @Inject
    lateinit var deviceAdminManager: DeviceAdminManager

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent?.action != SleepTimerManager.ACTION_SLEEP_TIMER_FINISHED) {
            return
        }

        val pendingResult = goAsync()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // 1. First, stop the timer and the foreground service.
                // This will clear the ongoing notification.
                sleepTimerManager.cancelTimer()

                // 2. Perform the final actions
                doNotDisturbManager.activateDoNotDisturb()
                deviceAdminManager.lockScreen()

                // 3. Finally, post a NEW, final notification that is not tied to the service.
                val finalNotification = SleepTimerService.createNotification(
                    context,
                    "El temporizador se completó. ¡Dulces sueños 🌙!", //"The Do Not Disturb mode has been activated.",
                    isOngoing = false
                )
                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(SleepTimerService.NOTIFICATION_ID, finalNotification)

            } finally {
                // The main work is done, just finish the broadcast receiver.
                pendingResult.finish()
            }
        }
    }
}
