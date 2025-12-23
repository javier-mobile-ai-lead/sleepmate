//package com.sleepmate.app.ui.screen.rest
//
//import android.content.ComponentName // Importante
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager // Importante
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import com.sleepmate.app.service.ScreenUnlockReceiver // Asegúrate de que esta ruta sea correcta
//import com.sleepmate.app.ui.theme.SleepMateTheme
//
//class RestActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            SleepMateTheme {
//                RestScreen(onDismiss = {
//                    // --- PASO 5: IMPLEMENTACIÓN ---
//
//                    // 1. Deshabilitamos el "Espía" (Receiver)
//                    // Esto es vital. Si no hacemos esto, CADA VEZ que desbloquees el celular
//                    // mañana o pasado, te saldrá esta pantalla. Queremos que salga solo una vez.
//                    try {
//                        val componentName = ComponentName(this@RestActivity, ScreenUnlockReceiver::class.java)
//                        packageManager.setComponentEnabledSetting(
//                            componentName,
//                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//                            PackageManager.DONT_KILL_APP
//                        )
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//
//                    // 2. Cerramos la actividad
//                    finish()
//                })
//            }
//        }
//    }
//
//    // Método estático para lanzar esta pantalla fácilmente
//    companion object {
//        fun start(context: Context) {
//            val intent = Intent(context, RestActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            }
//            context.startActivity(intent)
//        }
//    }
//}
//
//@Composable
//fun RestScreen(onDismiss: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFF121212)) // Fondo oscuro
//            .padding(32.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "🌙",
//            style = MaterialTheme.typography.displayLarge
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//        Text(
//            text = "Tu calidad de sueño es importante",
//            style = MaterialTheme.typography.headlineMedium,
//            color = Color.White,
//            textAlign = TextAlign.Center
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(
//            text = "El temporizador ha terminado. Es hora de descansar y dejar el teléfono.",
//            style = MaterialTheme.typography.bodyLarge,
//            color = Color.LightGray,
//            textAlign = TextAlign.Center
//        )
//        Spacer(modifier = Modifier.height(48.dp))
//        Button(onClick = onDismiss) {
//            Text("Entiendo, voy a descansar")
//        }
//    }
//}
