package com.sleepmate.app.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.sleepmate.app.R
import com.sleepmate.app.ui.screen.alarm.AlarmActivity
import com.sleepmate.domain.model.Alarm
import com.sleepmate.domain.repository.AlarmRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

enum class AlarmState {
    PLAYING,
    SNOOZED,
    STOPPED
}

@AndroidEntryPoint
class AlarmPlaybackService : LifecycleService() {

    @Inject
    lateinit var alarmRepository: AlarmRepository
    @Inject
    lateinit var wakeUpAlarmManager: WakeUpAlarmManager

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private var mediaPlayer: MediaPlayer? = null

    // --- Binder and StateFlow for Bound Service Architecture ---
    inner class LocalBinder : Binder() {
        fun getService(): AlarmPlaybackService = this@AlarmPlaybackService
    }
    private val binder = LocalBinder()

    private val _alarmState = MutableStateFlow(AlarmState.STOPPED)
    val alarmState: StateFlow<AlarmState> = _alarmState.asStateFlow()
    // ---------------------------------------------------------

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val action = intent?.action
        Timber.d("AlarmPlaybackService received action: $action")

        when (action) {
            ACTION_START -> {
                scope.launch {
                    _alarmState.value = AlarmState.PLAYING
                    val alarm = alarmRepository.getAlarm().first()
                    startPlaybackAndForeground(alarm)
                }
            }
            ACTION_STOP -> stopAlarm()
            ACTION_SNOOZE -> snoozeAlarm()
            ACTION_REFRESH_NOTIFICATION -> {
                scope.launch {
                    val alarm = alarmRepository.getAlarm().first()
                    refreshNotification(alarm)
                }
            }
        }

        return START_STICKY
    }

    // --- Public methods for Bound Service ---
    fun stopAlarm() {
        scope.launch {
            Timber.d("stopAlarm called.")
            _alarmState.value = AlarmState.STOPPED
            val alarm = alarmRepository.getAlarm().first()
            handleAlarmCompletion(alarm)
            stopAndRelease()
        }
    }

    fun snoozeAlarm() {
        Timber.d("snoozeAlarm called.")
        _alarmState.value = AlarmState.SNOOZED
        wakeUpAlarmManager.snooze()
        stopAndRelease()
    }
    // ----------------------------------------

    private fun startPlaybackAndForeground(alarm: Alarm) {
        val soundUri = getSoundUri(alarm.sound)
        if (soundUri != null) {
            mediaPlayer = MediaPlayer().apply {
                try {
                    setDataSource(applicationContext, soundUri)
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_ALARM)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build()
                    )
                    isLooping = true
                    prepareAsync()
                    setOnPreparedListener {
                        Timber.d("MediaPlayer prepared, starting playback.")
                        start()
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Error setting up MediaPlayer")
                    mediaPlayer = null
                }
            }
        }

        val notification = buildNotification(alarm, includeFullScreenIntent = true)
        startForeground(WAKE_UP_NOTIFICATION_ID, notification)
        Timber.d("Service started in foreground.")
    }

    private fun refreshNotification(alarm: Alarm) {
        Timber.d("Refreshing notification without full-screen intent.")
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = buildNotification(alarm, includeFullScreenIntent = false)
        notificationManager.notify(WAKE_UP_NOTIFICATION_ID, notification)
    }

    private fun buildNotification(alarm: Alarm, includeFullScreenIntent: Boolean): Notification {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(notificationManager)

        val activityIntent = Intent(this, AlarmActivity::class.java)

        val fullScreenPendingIntent = PendingIntent.getActivity(
            this,
            FULL_SCREEN_REQUEST_CODE,
            activityIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val contentPendingIntent = PendingIntent.getActivity(
            this,
            CONTENT_REQUEST_CODE,
            activityIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val stopIntent = Intent(this, AlarmPlaybackService::class.java).apply { action = ACTION_STOP }
        val stopPendingIntent = PendingIntent.getService(
            this,
            STOP_REQUEST_CODE,
            stopIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val snoozeIntent = Intent(this, AlarmPlaybackService::class.java).apply { action = ACTION_SNOOZE }
        val snoozePendingIntent = PendingIntent.getService(
            this,
            SNOOZE_REQUEST_CODE,
            snoozeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, WAKE_UP_NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle(alarm.name)
            .setContentText("Es hora de levantarse.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setContentIntent(contentPendingIntent)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setAutoCancel(false)
            .addAction(0, "Detener", stopPendingIntent)
            .addAction(0, "Pospener", snoozePendingIntent)
            .setOngoing(true)
            .setSound(null)

        if (includeFullScreenIntent) {
            Timber.d("Building notification with full-screen intent.")
            builder.setFullScreenIntent(fullScreenPendingIntent, true)
        }

        return builder.build()
    }

    private fun handleAlarmCompletion(alarm: Alarm) {
        if (alarm.days.isNotEmpty()) {
            wakeUpAlarmManager.schedule(alarm)
            Timber.d("Repeating alarm stopped. Rescheduled for next occurrence.")
        } else {
            scope.launch {
                val updatedAlarm = alarm.copy(isEnabled = false)
                alarmRepository.saveAlarm(updatedAlarm)
                Timber.d("One-time alarm stopped. Disabling it now.")
            }
        }
    }

    private fun stopAndRelease() {
        Timber.d("Stopping service and releasing resources.")
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun getSoundUri(soundName: String): Uri? {
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val soundNameLower = soundName.lowercase(Locale.ROOT)

        val soundResourceName = when (soundNameLower) {
            "mañana" -> "morning"
            "silencio" -> ""
            "predeterminado", "" -> "default"
            else -> soundNameLower.replace(" ", "_")
        }

        return when {
            soundResourceName.isEmpty() -> null
            soundResourceName == "default" -> defaultSoundUri
            else -> {
                val resourceId = resources.getIdentifier(soundResourceName, "raw", packageName)
                if (resourceId != 0) {
                    Uri.parse("android.resource://$packageName/$resourceId")
                } else {
                    Timber.w("Custom sound '$soundName' not found as '$soundNameLower'. Falling back to default.")
                    defaultSoundUri
                }
            }
        }
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                WAKE_UP_NOTIFICATION_CHANNEL_ID,
                "Wake Up Alarms",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for wake up alarms"
                setSound(null, null)
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _alarmState.value = AlarmState.STOPPED // Ensure state is cleared on destruction
        job.cancel()
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        return binder
    }

    companion object {
        const val ACTION_START = "com.sleepmate.app.service.ACTION_START"
        const val ACTION_STOP = "com.sleepmate.app.service.ACTION_STOP"
        const val ACTION_SNOOZE = "com.sleepmate.app.service.ACTION_SNOOZE"
        const val ACTION_REFRESH_NOTIFICATION = "com.sleepmate.app.service.ACTION_REFRESH_NOTIFICATION"

        private const val WAKE_UP_NOTIFICATION_CHANNEL_ID = "wake_up_alarms_service"
        private const val WAKE_UP_NOTIFICATION_ID = 2001
        private const val FULL_SCREEN_REQUEST_CODE = 2002
        private const val STOP_REQUEST_CODE = 2003
        private const val SNOOZE_REQUEST_CODE = 2004
        private const val CONTENT_REQUEST_CODE = 2005
    }
}
