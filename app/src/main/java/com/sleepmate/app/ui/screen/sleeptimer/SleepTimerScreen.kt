package com.sleepmate.app.ui.screen.sleeptimer

import android.app.admin.DevicePolicyManager
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SleepTimerScreen(
    onNavigateBack: () -> Unit,
    viewModel: SleepTimerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    var showPermissionDialog by remember { mutableStateOf(false) }

    // Launcher for Device Admin permission
    val adminPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        // Refresh the admin status after the user returns from the settings screen
        viewModel.checkDeviceAdminStatus()
    }

    // Handle error messages
    LaunchedEffect(uiState.error) {
        uiState.error?.let { error ->
            snackbarHostState.showSnackbar(error)
            viewModel.clearError()
        }
    }

    // Check permissions when screen appears
    LaunchedEffect(Unit) {
        viewModel.checkNotificationPolicyAccess()
        viewModel.checkDeviceAdminStatus()
    }

    // Re-check permissions when the app is resumed
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.checkNotificationPolicyAccess()
                viewModel.checkDeviceAdminStatus()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Sleep Timer",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },

                //Esto ya no va porque los permisos lo pide abajo de la pantalla
//                actions = {
//                    if (!uiState.hasNotificationPolicyAccess) {
//                        IconButton(onClick = { showPermissionDialog = true }) {
//                            Icon(Icons.Default.Settings, contentDescription = "Settings")
//                        }
//                    }
//                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {item {
            // Sleep Mode Status Card
            if (uiState.sleepModeActivated) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "🌙 Modo Sueño Activado",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "¡Que descanses bien!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Timer Status Text
            Text(
                text = when {
                    uiState.isTimerActive -> "Temporizador de Sueño Activo"
                    uiState.sleepModeActivated -> "Modo Sueño Completado"
                    else -> "Temporizador de Sueño"
                },
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Timer Circle with Progress
            Box(
                modifier = Modifier.size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(200.dp)) {
                    drawCircle(
                        color = Color.Gray.copy(alpha = 0.3f),
                        style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
                    )
                }

                if (uiState.isTimerActive) {
                    Canvas(modifier = Modifier.size(200.dp)) {
                        val sweepAngle = 360f * uiState.progress
                        drawArc(
                            color = Color(0xFF350BAD),
                            startAngle = -90f,
                            sweepAngle = sweepAngle,
                            useCenter = false,
                            style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }
                }

                Text(
                    text = uiState.timeRemaining,
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Light
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            if (!uiState.isTimerActive) {
                TimerDurationSlider(
                    selectedDuration = uiState.selectedDurationMinutes,
                    onDurationChange = { newDuration ->
                        viewModel.onDurationChange(newDuration)
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                FloatingActionButton(
                    onClick = {
                        if (uiState.isTimerActive) {
                            viewModel.cancelSleepTimer()
                        } else {
                            viewModel.startSleepTimer()
                        }
                    },
                    modifier = Modifier.size(80.dp),
                    containerColor = if (uiState.isTimerActive) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = if (uiState.isTimerActive) Icons.Default.Close else Icons.Default.PlayArrow,
                        contentDescription = if (uiState.isTimerActive) "Cancelar" else "Activar Sleep",
                        modifier = Modifier.size(40.dp),
                        tint = if (uiState.isTimerActive) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = when {
                    uiState.isTimerActive -> "Temporizador activo por ${uiState.selectedDurationMinutes}" + if(uiState.selectedDurationMinutes == 1) " minuto" else " minutos"
                    uiState.sleepModeActivated -> "El temporizador se completó. ¡Dulces sueños! 🌙"
                    else -> "Presiona para activar el temporizador de ${uiState.selectedDurationMinutes}" + if(uiState.selectedDurationMinutes == 1) " minuto" else " minutos"
                },
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            // --- PERMISSION SECTION ---
            if (!uiState.isTimerActive) {
                Spacer(modifier = Modifier.height(16.dp))

                // Notification Policy Permission
                if (!uiState.hasNotificationPolicyAccess) {
                    PermissionRequestCard(
                        title = "⚠️ Permiso de No Molestar",
                        text = "Para activar el modo No Molestar automáticamente, se necesita este permiso.",
                        onClick = { showPermissionDialog = true }
                    )
                }

                // Device Admin Permission
                if (!uiState.isDeviceAdminEnabled) {
                    Spacer(modifier = Modifier.height(8.dp))
                    PermissionRequestCard(
                        title = "🔒 Permiso para Apagar Pantalla",
                        text = "Opcional: Otorga este permiso si deseas que la pantalla se apague sola al finalizar el temporizador.",
                        onClick = {
                            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
                                putExtra(
                                    DevicePolicyManager.EXTRA_DEVICE_ADMIN,
                                    viewModel.adminComponentName
                                )
                                putExtra(
                                    DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                                    "Permite que la app bloquee la pantalla para la función de apagado automático."
                                )
                            }
                            adminPermissionLauncher.launch(intent)
                        }
                    )
                }
            }
        }
        }
    }
    
    // Dialog for Notification Policy
    if (showPermissionDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionDialog = false },
            title = { Text("Permiso para Modo No Molestar") },
            text = { Text("Para activar automáticamente el modo No Molestar cuando termine el temporizador, necesitas otorgar permisos en la configuración del sistema.") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.openNotificationPolicySettings()
                        showPermissionDialog = false
                    }
                ) { Text("Ir a Configuración") }
            },
            dismissButton = {
                TextButton(onClick = { showPermissionDialog = false }) { Text("Cancelar") }
            }
        )

    }
}

@Composable
fun PermissionRequestCard(
    title: String,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}


@Composable
fun TimerDurationSlider(
    selectedDuration: Int,
    onDurationChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Duración: $selectedDuration" + if(selectedDuration == 1) " minuto" else " minutos",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Slider(
            value = selectedDuration.toFloat(),
            onValueChange = { newValue ->
                onDurationChange(newValue.roundToInt())
            },
            valueRange = 1f..90f, 
            steps = 88, 
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
