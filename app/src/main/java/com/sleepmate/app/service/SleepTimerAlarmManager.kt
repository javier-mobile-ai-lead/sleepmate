package com.sleepmate.app.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SleepTimerAlarmManager @Inject constructor(
    private val context: Context
) {
    companion object {
        private const val SLEEP_TIMER_REQUEST_CODE = 1001
        const val ACTION_SLEEP_TIMER_FINISHED = "com.sleepmate.app.SLEEP_TIMER_FINISHED"
    }
    
    private val alarmManager: AlarmManager by lazy {
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }
    
    fun startSleepTimer(durationMinutes: Int = 25) {
        val intent = Intent(context, SleepTimerReceiver::class.java).apply {
            action = ACTION_SLEEP_TIMER_FINISHED
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            SLEEP_TIMER_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val triggerTime = System.currentTimeMillis() + (durationMinutes * 60 * 1000L)
        
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Use setExactAndAllowWhileIdle for better reliability on Doze mode
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }
            
            Timber.d("Sleep timer scheduled for ${durationMinutes} minutes from now")
        } catch (e: Exception) {
            Timber.e(e, "Failed to schedule sleep timer")
        }
    }
    
    fun cancelSleepTimer() {
        val intent = Intent(context, SleepTimerReceiver::class.java).apply {
            action = ACTION_SLEEP_TIMER_FINISHED
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            SLEEP_TIMER_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        alarmManager.cancel(pendingIntent)
        Timber.d("Sleep timer cancelled")
    }
}