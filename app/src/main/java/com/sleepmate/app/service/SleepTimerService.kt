package com.sleepmate.app.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.sleepmate.app.MainActivity
import com.sleepmate.app.R
import com.sleepmate.app.ui.screen.rest.RestActivity
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
    
    // Variable para mantener la referencia al receiver dinámico
    private var unlockReceiver: ScreenUnlockReceiver? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        // 1. Limpieza inicial: Aseguramos que no haya receiver basura registrado
        disableUnlockReceiver()

        serviceJob?.cancel()
        serviceJob = lifecycleScope.launch {
            sleepTimerManager.timerState.collectLatest { state ->
                if (state is TimerState.Active) {
                    startForeground(NOTIFICATION_ID, createNotification(this@SleepTimerService, "El temporizador está activo", isOngoing = true))

                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                    while (true) {
                        val remainingMillis = state.endTimeMillis - System.currentTimeMillis()

                        if (remainingMillis <= 0) {
                            // --- MOMENTO FINAL ---
                            Log.e("SleepMate", ">>> TIEMPO TERMINADO. Iniciando registro del Receiver... <<<")

                            // 2. Activamos el "Espía" (Receiver) DINÁMICAMENTE
                            enableUnlockReceiver()

                            // 3. Creamos una notificación final simple
                            val finalNotification = createNotification(
                                this@SleepTimerService,
                                "Hora de dormir. Deja el móvil 🌙", 
                                isOngoing = false
                            )

                            // 4. La enviamos
                            notificationManager.notify(NOTIFICATION_FINAL_ID, finalNotification)

                            // Rompemos el ciclo. El servicio sigue vivo (no llamamos stopSelf)
                            // esperando a que el receiver actúe.
                            break
                        }

                        val remainingTime = formatMillisToTime(remainingMillis)
                        // Actualizar notificación solo si cambia el segundo visible
                         if (remainingMillis % 1000 < 150) {
                            val updatedNotification = createNotification(this@SleepTimerService, "Tiempo restante: $remainingTime", isOngoing = true)
                            notificationManager.notify(NOTIFICATION_ID, updatedNotification)
                         }
                        delay(100) // Verificar con más frecuencia para precisión
                    }
                } else {
                    Log.d("SleepMate", "Estado inactivo. Deteniendo servicio.")
                    stopSelf()
                }
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        serviceJob?.cancel()
        disableUnlockReceiver() // Muy importante desregistrar para evitar fugas de memoria
        super.onDestroy()
    }

    // --- FUNCIONES ACTUALIZADAS PARA CONTROLAR EL RECEIVER ---

    private fun enableUnlockReceiver() {
        if (unlockReceiver == null) {
            try {
                unlockReceiver = ScreenUnlockReceiver()
                val filter = IntentFilter(Intent.ACTION_USER_PRESENT)
                
                // Registramos dinámicamente usando ContextCompat para compatibilidad con Android 13/14+
                // RECEIVER_EXPORTED es necesario para recibir broadcasts del sistema (como USER_PRESENT) en API 34+
                ContextCompat.registerReceiver(
                    this,
                    unlockReceiver,
                    filter,
                    ContextCompat.RECEIVER_EXPORTED
                )

                Log.e("SleepMate", ">>> RECEIVER ACTIVADO CORRECTAMENTE: Esperando desbloqueo de pantalla <<<")
            } catch (e: Exception) {
                Log.e("SleepMate", "ERROR FATAL al registrar receiver: ${e.message}")
                e.printStackTrace()
            }
        }
    }


    private fun disableUnlockReceiver() {
        unlockReceiver?.let { receiver ->
            try {
                unregisterReceiver(receiver)
                Log.d("SleepMate", "Receiver de desbloqueo DESACTIVADO")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        unlockReceiver = null
    }

    companion object {
        const val NOTIFICATION_ID = 1988
        const val NOTIFICATION_FINAL_ID = 1999
        private const val NOTIFICATION_CHANNEL_ID_ONGOING = "sleep_timer_channel_ongoing"
        private const val NOTIFICATION_CHANNEL_ID_FINAL = "sleep_timer_channel_final_v6"

        fun formatMillisToTime(millis: Long): String {
            val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(minutes)
            return String.format("%02d:%02d", minutes, seconds)
        }

        fun createNotification(context: Context, contentText: String, isOngoing: Boolean): Notification {
            val channelId = if (isOngoing) NOTIFICATION_CHANNEL_ID_ONGOING else NOTIFICATION_CHANNEL_ID_FINAL
            createNotificationChannel(context, isOngoing)

            // Intent principal al tocar la notificación (Lleva a MainActivity)
            val contentIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra("destination_route", "sleep_timer_screen")
            }

            val contentPendingIntent = PendingIntent.getActivity(
                context,
                if (isOngoing) 0 else 1,
                contentIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val title = if (isOngoing) "Temporizador Activo" else "Temporizador Finalizado"
            val priority = if (isOngoing) NotificationCompat.PRIORITY_LOW else NotificationCompat.PRIORITY_MAX
            val category = if (isOngoing) NotificationCompat.CATEGORY_SERVICE else NotificationCompat.CATEGORY_ALARM

            val builder = NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(contentText)
                .setSmallIcon(R.drawable.ic_logo)
                .setContentIntent(contentPendingIntent)
                .setOnlyAlertOnce(isOngoing)
                .setOngoing(isOngoing)
                .setAutoCancel(!isOngoing)
                .setPriority(priority)
                .setCategory(category)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

            if (!isOngoing) {
                builder.setDefaults(Notification.DEFAULT_ALL)
                
                // --- CAMBIO CLAVE: Full Screen Intent ---
                // Esto es lo que lanza la actividad automáticamente
                val fullScreenIntent = Intent(context, RestActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                val fullScreenPendingIntent = PendingIntent.getActivity(
                    context,
                    NOTIFICATION_FINAL_ID + 100, // Código de solicitud único
                    fullScreenIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                builder.setFullScreenIntent(fullScreenPendingIntent, true)
            }

            return builder.build()
        }

        fun createNotificationChannel(context: Context, isOngoing: Boolean) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                if (isOngoing) {
                    if (notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID_ONGOING) == null) {
                        val channel = NotificationChannel(
                            NOTIFICATION_CHANNEL_ID_ONGOING,
                            "Sleep Timer Active",
                            NotificationManager.IMPORTANCE_LOW
                        )
                        notificationManager.createNotificationChannel(channel)
                    }
                } else {
                    if (notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID_FINAL) == null) {
                        val channel = NotificationChannel(
                            NOTIFICATION_CHANNEL_ID_FINAL,
                            "Sleep Timer Finished",
                            NotificationManager.IMPORTANCE_HIGH
                        ).apply {
                            description = "Notificación al finalizar el temporizador"
                            enableVibration(true)
                            enableLights(true)
                            setBypassDnd(true)
                            lockscreenVisibility = Notification.VISIBILITY_PUBLIC

                            val audioAttributes = AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                                .setUsage(AudioAttributes.USAGE_ALARM)
                                .build()
                            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), audioAttributes)
                        }
                        notificationManager.createNotificationChannel(channel)
                    }
                }
            }
        }
    }
}
