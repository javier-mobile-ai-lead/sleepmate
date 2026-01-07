package com.sleepmate.app.ui.screen.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupProfileScreen(
    onFinish: () -> Unit,
    viewModel: SetupProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentStep by viewModel.currentStep.collectAsState()
    val totalSteps = 5

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
                1 -> AgeRangeStep(
                    selectedRange = uiState.ageRange,
                    onRangeSelected = {
                        viewModel.updateAgeRange(it)
                        viewModel.nextStep()
                    }
                )
                2 -> GoalsStep(
                    selectedGoals = uiState.goals,
                    onToggleGoal = viewModel::toggleGoal,
                    onNext = viewModel::nextStep
                )
                3 -> StressAndStimulantsStep(
                    stressLevel = uiState.stressLevel,
                    onStressChange = viewModel::updateStressLevel,
                    stimulantConsumption = uiState.stimulantConsumption,
                    onStimulantChange = viewModel::updateStimulantConsumption,
                    onNext = viewModel::nextStep
                )
                4 -> PermissionsStep(
                    onFinish = { viewModel.saveProfile(onFinish) }
                )
            }
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
fun AgeRangeStep(selectedRange: String, onRangeSelected: (String) -> Unit) {
    val ranges = listOf("18 a 25 años", "26 a 30 años", "31 años a más")
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("¿Cuál es tu edad?", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))
        ranges.forEach { range ->
            OutlinedButton(
                onClick = { onRangeSelected(range) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = MaterialTheme.shapes.medium,
                colors = if (selectedRange == range) ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer) else ButtonDefaults.outlinedButtonColors()
            ) {
                Text(range)
            }
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
fun StressAndStimulantsStep(
    stressLevel: Int,
    onStressChange: (Int) -> Unit,
    stimulantConsumption: String,
    onStimulantChange: (String) -> Unit,
    onNext: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Cuéntanos sobre tu día", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        
        Text("Nivel de estrés (1-5)", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = stressLevel.toFloat(),
            onValueChange = { onStressChange(it.toInt()) },
            valueRange = 1f..5f,
            steps = 3
        )
        Text("Nivel seleccionado: $stressLevel", style = MaterialTheme.typography.bodySmall)
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text("¿Consumes estimulantes? (Café/Bebidas)", style = MaterialTheme.typography.titleMedium)
        val options = listOf("Nunca", "Ocasionalmente", "Diariamente")
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            options.forEach { option ->
                FilterChip(
                    selected = stimulantConsumption == option,
                    onClick = { onStimulantChange(option) },
                    label = { Text(option) }
                )
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onNext,
            enabled = stimulantConsumption.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Siguiente")
        }
    }
}

@Composable
fun PermissionsStep(onFinish: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        Text("Sincroniza tu salud", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Para que nuestra IA te dé consejos precisos, necesitamos acceso a tus datos de Actividad Física y Salud (Health Connect).",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = { /* Aquí iría la lógica de solicitud de permisos */ },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Conectar Health Connect")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        TextButton(onClick = onFinish) {
            Text("Omitir por ahora y finalizar")
        }
    }
}
