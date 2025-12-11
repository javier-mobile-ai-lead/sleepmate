package com.sleepmate.app.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.sleepmate.domain.model.Alarm
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WakeUpAlarmManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun schedule(alarm: Alarm): Long? {
        if (!alarm.isEnabled) {
            Timber.d("Alarm is disabled, not scheduling.")
            cancel() // Cancel any previously scheduled alarm
            return null
        }

        val intent = Intent(context, WakeUpAlarmReceiver::class.java).apply {
            action = WakeUpAlarmReceiver.ACTION_WAKE_UP_ALARM
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            WAKE_UP_ALARM_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val timeParts = alarm.time.split(":").mapNotNull { it.toIntOrNull() }
        if (timeParts.size != 2) {
            Timber.e("Invalid time format for alarm: ${alarm.time}")
            return null
        }
        val hour = timeParts[0]
        val minute = timeParts[1]
        val calendarDays = mapDaysToCalendarDays(alarm.days)

        val nextTriggerTime = calculateNextTriggerTime(hour, minute, calendarDays)

        if (nextTriggerTime != null) {
            try {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    nextTriggerTime,
                    pendingIntent
                )
                val triggerTime = Calendar.getInstance().apply { timeInMillis = nextTriggerTime }
                Timber.d("Wake-up alarm scheduled for: %s", triggerTime.time)
                return nextTriggerTime
            } catch (e: SecurityException) {
                Timber.e(e, "Could not schedule exact alarm due to missing permission.")
                return null
            }
        } else {
             // This case should not be reachable if the logic in calculateNextTriggerTime is correct
             Timber.w("Could not calculate next trigger time. Canceling alarm.")
             cancel()
             return null
        }
    }

    fun snooze() {
        val snoozeTime = System.currentTimeMillis() + 10 * 60 * 1000 // 10 minutes from now
        val intent = Intent(context, WakeUpAlarmReceiver::class.java).apply {
            action = WakeUpAlarmReceiver.ACTION_WAKE_UP_ALARM // The receiver will just start the service
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            SNOOZE_ALARM_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, snoozeTime, pendingIntent)
        Timber.d("Alarm snoozed for 10 minutes.")
    }

    fun cancel() {
        // Cancel the main alarm
        val mainIntent = Intent(context, WakeUpAlarmReceiver::class.java).apply {
            action = WakeUpAlarmReceiver.ACTION_WAKE_UP_ALARM
        }
        val mainPendingIntent = PendingIntent.getBroadcast(
            context,
            WAKE_UP_ALARM_REQUEST_CODE,
            mainIntent,
            PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
        )
        if (mainPendingIntent != null) {
            alarmManager.cancel(mainPendingIntent)
            Timber.d("Main wake-up alarm canceled.")
        }

        // Cancel any snoozed alarm
        val snoozeIntent = Intent(context, WakeUpAlarmReceiver::class.java).apply {
            action = WakeUpAlarmReceiver.ACTION_WAKE_UP_ALARM
        }
        val snoozePendingIntent = PendingIntent.getBroadcast(
            context,
            SNOOZE_ALARM_REQUEST_CODE,
            snoozeIntent,
            PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
        )
        if (snoozePendingIntent != null) {
            alarmManager.cancel(snoozePendingIntent)
            Timber.d("Snoozed wake-up alarm canceled.")
        }
    }

    private fun mapDaysToCalendarDays(days: List<String>): List<Int> {
        val dayMap = mapOf(
            "D" to Calendar.SUNDAY,
            "L" to Calendar.MONDAY,
            "M" to Calendar.TUESDAY,
            "X" to Calendar.WEDNESDAY,
            "J" to Calendar.THURSDAY,
            "V" to Calendar.FRIDAY,
            "S" to Calendar.SATURDAY
        )
        return days.mapNotNull { dayMap[it] }
    }

    private fun calculateNextTriggerTime(hour: Int, minute: Int, days: List<Int>): Long? {
        val now = Calendar.getInstance()

        // Case 1: No repeating days are selected (one-time alarm)
        if (days.isEmpty()) {
            val trigger = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            // If the time has already passed for today, schedule it for tomorrow
            if (trigger.before(now)) {
                trigger.add(Calendar.DAY_OF_YEAR, 1)
            }
            return trigger.timeInMillis
        }

        // Case 2: Repeating alarm
        // Iterate through the next 7 days (starting from today) to find the nearest valid alarm day
        for (i in 0..7) {
            val potentialDay = Calendar.getInstance().apply {
                add(Calendar.DAY_OF_YEAR, i)
            }
            val dayOfWeek = potentialDay.get(Calendar.DAY_OF_WEEK)

            if (dayOfWeek in days) {
                // We found a valid day. Now set the time.
                val trigger = Calendar.getInstance().apply {
                    time = potentialDay.time
                    set(Calendar.HOUR_OF_DAY, hour)
                    set(Calendar.MINUTE, minute)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }

                // If this trigger time is in the future, we use it
                if (trigger.after(now)) {
                    return trigger.timeInMillis
                }
            }
        }

        return null // Should not happen if days are selected
    }

    companion object {
        private const val WAKE_UP_ALARM_REQUEST_CODE = 1003
        private const val SNOOZE_ALARM_REQUEST_CODE = 1004
    }
}
