package com.sleepmate.app.ui.screen.habits

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sleepmate.app.R
import com.sleepmate.app.util.AnalyticsHelper
import com.sleepmate.domain.model.SuggestedHabit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHabitBottomSheet(
    onDismiss: () -> Unit,
    onAddHabit: (title: String, subtitle: String?, isSuggested: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var selectedTab by remember { mutableStateOf(0) }
    var customTitle by remember { mutableStateOf("") }
    var customSubtitle by remember { mutableStateOf("") }
    var titleError by remember { mutableStateOf<String?>(null) }
    var subtitleError by remember { mutableStateOf<String?>(null) }
    
    val suggestedHabits = remember {
        listOf(
            SuggestedHabit(
                title = "Meditación nocturna",
                subtitle = "5-10 minutos de meditación"
            ),
            SuggestedHabit(
                title = "Lectura relajante", 
                subtitle = "15-20 minutos sin pantallas"
            ),
            SuggestedHabit(
                title = "Té de manzanilla",
                subtitle = "Una taza caliente antes de dormir"
            ),
            SuggestedHabit(
                title = "Ducha tibia",
                subtitle = "Relajar el cuerpo y la mente"
            ),
            SuggestedHabit(
                title = "Diario de gratitud",
                subtitle = "Escribir 3 cosas positivas del día"
            ),
            SuggestedHabit(
                title = "Estiramientos",
                subtitle = "5 minutos de yoga suave"
            )
        )
    }
    
    fun validateAndSave() {
        if (selectedTab == 1) { // Custom habit
            titleError = null
            subtitleError = null
            
            when {
                customTitle.isBlank() -> {
                    titleError = "El título no puede estar vacío"
                    return
                }
                customTitle.length > 50 -> {
                    titleError = "El título es demasiado largo"
                    return
                }
                customSubtitle.length > 100 -> {
                    subtitleError = "La descripción es demasiado larga"
                    return
                }
            }
            
            keyboardController?.hide()
            onAddHabit(
                customTitle.trim(),
                customSubtitle.trim().takeIf { it.isNotEmpty() },
                false
            )
        }
    }
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
            )
            .padding(16.dp)
    ) {
        // Handle
        Box(
            modifier = Modifier
                .size(width = 32.dp, height = 4.dp)
                .background(
                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                    RoundedCornerShape(2.dp)
                )
                .align(Alignment.CenterHorizontally)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.add_habit_title),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            
            IconButton(onClick = onDismiss) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = stringResource(R.string.cancel),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Tabs
        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.surface,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = {
                    Text(
                        stringResource(R.string.suggested_habits),
                        fontSize = 14.sp
                    )
                }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = {
                    Text(
                        stringResource(R.string.custom_habit),
                        fontSize = 14.sp
                    )
                }
            )
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .animateContentSize()
        ) {
            when (selectedTab) {
                0 -> {
                    // Suggested Habits
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(suggestedHabits) { habit ->
                            SuggestedHabitCard(
                                habit = habit,
                                onClick = {
                                    onAddHabit(habit.title, habit.subtitle, true)
                                }
                            )
                        }
                    }
                }
                1 -> {
                    // Custom Habit Form
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutlinedTextField(
                            value = customTitle,
                            onValueChange = { 
                                customTitle = it
                                titleError = null
                            },
                            label = { Text(stringResource(R.string.habit_title_label)) },
                            placeholder = { Text(stringResource(R.string.habit_title_hint)) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            isError = titleError != null,
                            supportingText = titleError?.let { error ->
                                { Text(error, color = MaterialTheme.colorScheme.error) }
                            }
                        )
                        
                        OutlinedTextField(
                            value = customSubtitle,
                            onValueChange = { 
                                customSubtitle = it
                                subtitleError = null
                            },
                            label = { Text(stringResource(R.string.habit_subtitle_label)) },
                            placeholder = { Text(stringResource(R.string.habit_subtitle_hint)) },
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 3,
                            isError = subtitleError != null,
                            supportingText = subtitleError?.let { error ->
                                { Text(error, color = MaterialTheme.colorScheme.error) }
                            }
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = onDismiss,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(stringResource(R.string.cancel))
                            }
                            
                            Button(
                                onClick = {
                                    AnalyticsHelper.logClick("save_habit_button", "AddHabitBottomSheet")
                                    validateAndSave()},
                                modifier = Modifier.weight(1f),
                                enabled = customTitle.isNotBlank()
                            ) {
                                Text(stringResource(R.string.save_habit))
                            }
                        }
                    }   
                }
            }
        }
    }
}

@Composable
private fun SuggestedHabitCard(
    habit: SuggestedHabit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = habit.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = habit.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}