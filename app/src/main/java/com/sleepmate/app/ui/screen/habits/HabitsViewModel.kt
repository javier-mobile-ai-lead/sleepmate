package com.sleepmate.app.ui.screen.habits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.domain.model.DailySleepProgress
import com.sleepmate.domain.model.SleepHabit
import com.sleepmate.domain.repository.SleepHabitRepository
import com.sleepmate.domain.repository.SleepProgressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

data class HabitsUiState(
    val habits: List<SleepHabit> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showBottomSheet: Boolean = false,
    val completionMessage: String? = null,
    val showInterstitialAd: Boolean = false // Estado para mostrar el anuncio
)

@HiltViewModel
class HabitsViewModel @Inject constructor(
    private val sleepHabitRepository: SleepHabitRepository,
    private val sleepProgressRepository: SleepProgressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HabitsUiState())
    val uiState: StateFlow<HabitsUiState> = _uiState.asStateFlow()

    init {
        observeHabits()
        recalculateProgressOnHabitChange()
    }

    private fun observeHabits() {
        viewModelScope.launch {
            try {
                sleepHabitRepository.getSleepHabits()
                    .catch { exception ->
                        Timber.e(exception, "Error observing habits")
                        _uiState.value = _uiState.value.copy(
                            error = "Error al cargar los hábitos",
                            isLoading = false
                        )
                    }
                    .collect { habits ->
                        _uiState.value = _uiState.value.copy(
                            habits = habits,
                            isLoading = false,
                            error = null
                        )
                    }
            } catch (e: Exception) {
                Timber.e(e, "Failed to observe habits")
                _uiState.value = _uiState.value.copy(
                    error = "Error al cargar los hábitos",
                    isLoading = false
                )
            }
        }
    }

    private fun recalculateProgressOnHabitChange() {
        viewModelScope.launch {
            uiState.map { it.habits }
                .distinctUntilChanged()
                .drop(1) // Drop initial empty list
                .collect { habits ->
                    try {
                        val todayProgress = DailySleepProgress(
                            date = LocalDate.now(),
                            habits = habits
                        )
                        sleepProgressRepository.saveDailyProgress(todayProgress)
                        Timber.d("Daily progress recalculated due to habit change.")
                    } catch (e: Exception) {
                        Timber.e(e, "Failed to recalculate daily progress")
                    }
                }
        }
    }

    fun showAddHabitBottomSheet() {
        _uiState.value = _uiState.value.copy(showBottomSheet = true)
    }

    fun hideAddHabitBottomSheet() {
        _uiState.value = _uiState.value.copy(showBottomSheet = false)
    }

    fun addHabit(title: String, subtitle: String?, isSuggested: Boolean) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, error = null)

                val newHabit = SleepHabit(
                    title = title,
                    subtitle = subtitle,
                    isSuggested = isSuggested,
                    isCompleted = false
                )

                sleepHabitRepository.addSleepHabit(newHabit)

                _uiState.value = _uiState.value.copy(
                    showBottomSheet = false,
                    isLoading = false,
                    error = null
                )

                Timber.d("Habit added successfully: $title")

            } catch (e: Exception) {
                Timber.e(e, "Failed to add habit")
                _uiState.value = _uiState.value.copy(
                    error = "Error al guardar el hábito",
                    isLoading = false
                )
            }
        }
    }

    fun toggleHabitComplete(habit: SleepHabit) {
        viewModelScope.launch {
            try {
                val updatedHabit = habit.copy(isCompleted = !habit.isCompleted)
                sleepHabitRepository.updateSleepHabit(updatedHabit)

                val message = if (updatedHabit.isCompleted) {
                    "¡Hábito completado!"
                } else {
                    "Hábito marcado como incompleto"
                }

                // Lógica de "Victoria": Verificar si TODOS los hábitos están completados
                var showInterstitial = false
                if (updatedHabit.isCompleted) {
                    val currentHabits = _uiState.value.habits
                    // Verificamos si todos los DEMÁS están completados
                    val allOthersCompleted = currentHabits.none { it.id != habit.id && !it.isCompleted }
                    if (allOthersCompleted && currentHabits.isNotEmpty()) {
                        showInterstitial = true
                        Timber.d("All habits completed! Triggering Interstitial Ad.")
                    }
                }

                _uiState.value = _uiState.value.copy(
                    completionMessage = message,
                    showInterstitialAd = showInterstitial
                )

                Timber.d("Habit completion toggled: ${habit.title}")

            } catch (e: Exception) {
                Timber.e(e, "Failed to toggle habit completion")
                _uiState.value = _uiState.value.copy(
                    error = "Error al actualizar el hábito"
                )
            }
        }
    }

    fun adShown() {
        _uiState.value = _uiState.value.copy(showInterstitialAd = false)
    }

    fun deleteHabit(habitId: String) {
        viewModelScope.launch {
            try {
                sleepHabitRepository.deleteSleepHabit(habitId)
                Timber.d("Habit deleted successfully: $habitId")
            } catch (e: Exception) {
                Timber.e(e, "Failed to delete habit")
                _uiState.value = _uiState.value.copy(
                    error = "Error al eliminar el hábito"
                )
            }
        }
    }

    fun updateHabit(habit: SleepHabit) {
        viewModelScope.launch {
            try {
                sleepHabitRepository.updateSleepHabit(habit)
                Timber.d("Habit updated successfully: ${habit.title}")

            } catch (e: Exception) {
                Timber.e(e, "Failed to update habit")
                _uiState.value = _uiState.value.copy(
                    error = "Error al actualizar el hábito"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun clearCompletionMessage() {
        _uiState.value = _uiState.value.copy(completionMessage = null)
    }

    fun retryLoadingHabits() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        observeHabits()
    }
}
