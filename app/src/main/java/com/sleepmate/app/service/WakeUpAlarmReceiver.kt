package com.sleepmate.app.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class WakeUpAlarmReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_WAKE_UP_ALARM = "com.sleepmate.app.WAKE_UP_ALARM"
    }

    override fun onReceive(context: Context, intent: Intent?) {
        Timber.d("Wake-up alarm received, starting AlarmPlaybackService.")
        if (intent?.action != ACTION_WAKE_UP_ALARM) return

        val serviceIntent = Intent(context, AlarmPlaybackService::class.java).apply {
            action = AlarmPlaybackService.ACTION_START
            // The service will be responsible for fetching the current alarm settings
        }

        // Start the service in a way that's compatible with modern Android versions.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }
}
