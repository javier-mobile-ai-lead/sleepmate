package com.sleepmate.app.service

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import com.sleepmate.app.ui.screen.rest.RestActivity

class ScreenUnlockReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_USER_PRESENT) {
            Log.e("SleepMate", ">>> USER_PRESENT detectado. Iniciando secuencia de lanzamiento de RestActivity...")

            if (!Settings.canDrawOverlays(context)) {
                Log.e("SleepMate", "ERROR: Permiso 'Mostrar sobre otras apps' NO concedido.")
                return
            }

            try {
                // Estrategia mejorada: Usar PendingIntent para lanzar la actividad
                // Esto suele saltarse algunas restricciones de inicio en background en ciertos fabricantes.
                val activityIntent = Intent(context, RestActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
                    addCategory(Intent.CATEGORY_LAUNCHER) 
                }

                val pendingIntent = PendingIntent.getActivity(
                    context,
                    999,
                    activityIntent,
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )

                Log.e("SleepMate", "Intentando lanzar RestActivity vía PendingIntent...")
                pendingIntent.send()
                
            } catch (e: Exception) {
                Log.e("SleepMate", "FALLÓ el lanzamiento de RestActivity: ${e.message}")
                e.printStackTrace()
                
                // Fallback: Intento clásico si falla el PendingIntent
                try {
                    val fallbackIntent = Intent(context, RestActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    context.startActivity(fallbackIntent)
                } catch (e2: Exception) {
                    Log.e("SleepMate", "FALLÓ también el método clásico: ${e2.message}")
                }
            }
        }
    }
}
