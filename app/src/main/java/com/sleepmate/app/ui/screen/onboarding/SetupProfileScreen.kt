package com.sleepmate.app.ui.screen.onboarding

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.health.connect.client.PermissionController
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupProfileScreen(
    viewModel: SetupProfileViewModel,
    onFinish: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentStep by viewModel.currentStep.collectAsState()
    val healthConnectAvailable by viewModel.healthConnectAvailable.collectAsState()
    val permissionsState by viewModel.permissionsState.collectAsState()
    val isSyncing by viewModel.isSyncing.collectAsState()
    
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val totalSteps = 6 // Name, Age, Goals, Stress, Caffeine, Permissions

    // PRO FEATURE: Automatically refresh permissions when returning to the app
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.refreshPermissions()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val requestPermissionsLauncher = rememberLauncherForActivityResult(
        contract = PermissionController.createRequestPermissionResultContract()
    ) { grantedPermissions ->
        viewModel.onPermissionsResult(grantedPermissions)
    }

    if (isSyncing) {
        SyncingLoadingScreen()
    } else if(currentStep < totalSteps){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Personaliza tu experiencia") },
                    navigationIcon = {
                        if (currentStep > 0) {
                            IconButton(onClick = { viewModel.previousStep() }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                            }
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    progress = { (currentStep + 1) / totalSteps.toFloat() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                )

                when (currentStep) {
                    0 -> NameStep(
                        name = uiState.name,
                        onNameChange = viewModel::updateName,
                        onNext = viewModel::nextStep
                    )
                    1 -> AgeStep(
                        selectedAge = uiState.age,
                        onAgeSelected = viewModel::updateAge,
                        onNext = viewModel::nextStep
                    )
                    2 -> GoalsStep(
                        selectedGoals = uiState.goals,
                        onToggleGoal = viewModel::toggleGoal,
                        onNext = viewModel::nextStep
                    )
                    3 -> StressStep(
                        stressLevel = uiState.stressLevel,
                        onStressChange = viewModel::updateStressLevel,
                        onNext = viewModel::nextStep
                    )
                    4 -> CaffeineStep(
                        selectedOption = uiState.caffeineConsumption,
                        onOptionSelected = viewModel::updateCaffeineConsumption,
                        onNext = viewModel::nextStep
                    )
                    5 -> PermissionsStep(
                        healthConnectAvailable = healthConnectAvailable,
                        permissionsState = permissionsState,
                        onConnectClick = {
                            requestPermissionsLauncher.launch(viewModel.getHealthConnectPermissions())
                        },
                        onOpenSettings = {
                            context.startActivity(viewModel.getHealthConnectSettingsIntent())
                        },
                        onFinish = { viewModel.saveProfile(onFinish) }
                    )
                }
            }
        }
    }
    else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
    }
}

@Composable
fun SyncingLoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 6.dp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Sincronizando con Health Connect",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,

                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Estamos obteniendo tus datos de salud para que SleepMate pueda darte los mejores consejos personalizados.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Esto solo tomará unos segundos...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun NameStep(name: String, onNameChange: (String) -> Unit, onNext: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("¿Cómo te llamas?", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text("La IA te saludará por tu nombre para una experiencia más cercana.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Tu nombre") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onNext,
            enabled = name.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Siguiente")
        }
    }
}

@Composable
fun AgeStep(selectedAge: Int?, onAgeSelected: (Int) -> Unit, onNext: () -> Unit) {
    val age = selectedAge ?: 25
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("¿Cuál es tu edad?", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Conocer tu edad ayuda a la IA a entender tus ciclos biológicos de sueño.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(text = "$age años", style = MaterialTheme.typography.displayMedium, color = MaterialTheme.colorScheme.primary)
        
        Slider(
            value = age.toFloat(),
            onValueChange = { onAgeSelected(it.toInt()) },
            valueRange = 12f..100f,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Siguiente")
        }
    }
}

@Composable
fun GoalsStep(
    selectedGoals: List<String>,
    onToggleGoal: (String) -> Unit,
    onNext: () -> Unit
) {
    val options = listOf("Dormir más rápido", "Mejorar calidad de sueño", "Reducir estrés nocturno", "Despertar con energía")
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("¿Cuál es tu objetivo principal?", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(options) { option ->
                val isSelected = selectedGoals.contains(option)
                FilterChip(
                    selected = isSelected,
                    onClick = { onToggleGoal(option) },
                    label = { Text(option, modifier = Modifier.padding(8.dp)) },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
            }
        }
        Button(
            onClick = onNext,
            enabled = selectedGoals.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Siguiente")
        }
    }
}

@Composable
fun StressStep(
    stressLevel: Int,
    onStressChange: (Int) -> Unit,
    onNext: () -> Unit
) {
    val stressLabels = listOf("Muy bajo", "Bajo", "Moderado", "Alto", "Muy alto")
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Nivel de Estrés Percibido", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text("¿Cómo calificarías tu nivel de estrés general últimamente?", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            text = stressLabels[stressLevel - 1],
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
        
        Slider(
            value = stressLevel.toFloat(),
            onValueChange = { onStressChange(it.toInt()) },
            valueRange = 1f..5f,
            steps = 3,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Siguiente")
        }
    }
}

@Composable
fun CaffeineStep(
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    onNext: () -> Unit
) {
    val options = listOf(
        "No consumo cafeína",
        "Solo por las mañanas",
        "1-2 tazas (mañana y tarde)",
        "3+ tazas o bebidas energéticas",
        "Consumo antes de dormir"
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Consumo de Cafeína", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text("La cafeína tiene una vida media de 6 horas. Esto es clave para tu análisis.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(24.dp))
        
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(options) { option ->
                val isSelected = selectedOption == option
                FilterChip(
                    selected = isSelected,
                    onClick = { onOptionSelected(option) },
                    label = { Text(option, modifier = Modifier.padding(8.dp)) },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
            }
        }
        
        Button(
            onClick = onNext,
            enabled = selectedOption.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Siguiente")
        }
    }
}

@Composable
fun PermissionsStep(
    healthConnectAvailable: Boolean,
    permissionsState: HealthPermissionsState,
    onConnectClick: () -> Unit,
    onOpenSettings: () -> Unit,
    onFinish: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Sincroniza tu salud", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Para que nuestra IA te dé consejos precisos, necesitamos acceso a tus datos de Actividad Física y Salud.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))

        if (healthConnectAvailable) {
            AnimatedVisibility(visible = permissionsState != HealthPermissionsState.NotRequested) {
                PermissionFeedback(permissionsState)
            }

            Spacer(modifier = Modifier.height(16.dp))

            val isDenied = permissionsState is HealthPermissionsState.Denied
            val isAllGranted = permissionsState is HealthPermissionsState.AllGranted
            val isPartially = permissionsState is HealthPermissionsState.PartiallyGranted

            Button(
                onClick = { 
                    if (isDenied) onOpenSettings() else onConnectClick() 
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                enabled = !isAllGranted,
                colors = ButtonDefaults.buttonColors(
                    containerColor = when {
                        isAllGranted -> Color(0xFF4CAF50)
                        isDenied -> MaterialTheme.colorScheme.error
                        else -> MaterialTheme.colorScheme.secondary
                    }
                )
            ) {
                val icon = when {
                    isAllGranted -> Icons.Default.CheckCircle
                    isDenied -> Icons.Default.Settings
                    else -> null
                }
                val text = when {
                    isAllGranted -> "Conexión exitosa"
                    isDenied -> "Abrir Ajustes de Salud"
                    isPartially -> "Completar permisos"
                    else -> "Conectar Health Connect"
                }

                if (icon != null) {
                    Icon(icon, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                }
                Text(text)
            }

            if (isDenied || isPartially) {
                Spacer(modifier = Modifier.height(12.dp))
                
                if (isPartially) {
                    TextButton(
                        onClick = onOpenSettings,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("¿El diálogo no aparece? Configurar en Ajustes")
                    }
                }

                Text(
                    text = if (isDenied) 
                        "El sistema ha bloqueado el acceso. Por favor, activa los permisos manualmente en los Ajustes de Salud." 
                    else 
                        "Si el diálogo de permisos no aparece después de varios intentos, el sistema puede haberlo bloqueado. Usa el enlace de arriba para configurar manualmente.",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isDenied) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onFinish,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(if (isAllGranted) "Finalizar" else "Continuar de todos modos")
            }
        } else {
            Text(
                "Health Connect no está disponible en este dispositivo, pero puedes continuar.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onFinish,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Finalizar")
            }
        }
    }
}

@Composable
fun PermissionFeedback(state: HealthPermissionsState) {
    val (message, color, icon) = when (state) {
        HealthPermissionsState.AllGranted -> Triple("¡Todo listo! Datos sincronizados.", Color(0xFF4CAF50), Icons.Default.CheckCircle)
        is HealthPermissionsState.PartiallyGranted -> Triple("Faltan ${state.totalCount - state.grantedCount} permisos para que la IA sea más potente.", Color(0xFFFF9800), Icons.Default.Warning)
        HealthPermissionsState.Denied -> Triple("Los permisos están desactivados.", Color(0xFFF44336), Icons.Default.Warning)
        else -> Triple("", Color.Transparent, Icons.Default.Warning)
    }

    if (message.isNotEmpty()) {
        Card(
            colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(icon, contentDescription = null, tint = color)
                Spacer(modifier = Modifier.width(12.dp))
                Text(message, style = MaterialTheme.typography.bodySmall, color = color, fontWeight = FontWeight.Medium)
            }
        }
    }
}
