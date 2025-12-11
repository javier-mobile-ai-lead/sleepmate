package com.sleepmate.app.ui.screen.alarm

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.sleepmate.app.service.AlarmPlaybackService
import com.sleepmate.app.service.AlarmState
import com.sleepmate.app.ui.theme.SleepMateTheme
import kotlinx.coroutines.launch

class AlarmActivity : ComponentActivity() {

    private var isHandled = false
    private var alarmService: AlarmPlaybackService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as AlarmPlaybackService.LocalBinder
            alarmService = binder.getService()
            isBound = true

            // Start collecting the alarm state.
            // When the state changes to a terminal one, finish the activity.
            lifecycleScope.launch {
                alarmService?.alarmState?.collect { state ->
                    if (state == AlarmState.STOPPED || state == AlarmState.SNOOZED) {
                        isHandled = true
                        if (!isFinishing) {
                            finish()
                        }
                    }
                }
            }
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            isBound = false
            alarmService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // These flags are crucial for showing the activity over the lock screen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        } else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
            )
        }

        setContent {
            SleepMateTheme {
                AlarmScreen(
                    onStop = {
                        isHandled = true
                        alarmService?.stopAlarm()
                        finish()
                    },
                    onSnooze = {
                        isHandled = true
                        alarmService?.snoozeAlarm()
                        finish()
                    }
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Bind to the service
        Intent(this, AlarmPlaybackService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        // Unbind from the service
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!isHandled) {
            // If the activity is destroyed without the user pressing Stop or Snooze
            // (e.g., by pressing the back button), the alarm is NOT handled.
            // We tell the service to refresh its notification so it remains visible.
            // We use sendActionToService because the service might be unbound at this point.
            sendActionToService(AlarmPlaybackService.ACTION_REFRESH_NOTIFICATION)
        }
    }

    private fun sendActionToService(action: String) {
        val intent = Intent(this, AlarmPlaybackService::class.java).apply {
            this.action = action
        }
        startService(intent)
    }
}

@Composable
fun AlarmScreen(onStop: () -> Unit, onSnooze: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Es hora de levantarse", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(64.dp))
            Button(onClick = onStop) {
                Text("Detener")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onSnooze) {
                Text("Pospener 10 minutos")
            }
        }
    }
}

