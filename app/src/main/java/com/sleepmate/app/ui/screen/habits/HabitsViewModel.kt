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
    val completionMessage: String? = null
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

                // Recalculate and save daily progress
                val allHabits = sleepHabitRepository.getSleepHabits().first()
                val todayProgress = DailySleepProgress(
                    date = LocalDate.now(),
                    habits = allHabits
                )
                sleepProgressRepository.saveDailyProgress(todayProgress)

                // Show completion message
                val message = if (updatedHabit.isCompleted) {
                    "¡Hábito completado!"
                } else {
                    "Hábito marcado como incompleto"
                }

                _uiState.value = _uiState.value.copy(completionMessage = message)

                Timber.d("Habit completion toggled: ${habit.title}")

            } catch (e: Exception) {
                Timber.e(e, "Failed to toggle habit completion")
                _uiState.value = _uiState.value.copy(
                    error = "Error al actualizar el hábito"
                )
            }
        }
    }

    fun deleteHabit(habitId: String) {
        viewModelScope.launch {
            try {
                sleepHabitRepository.deleteSleepHabit(habitId)
                Timber.d("Habit deleted successfully: $habitId")

                // Recalculate and save daily progress after deletion
                val allHabits = sleepHabitRepository.getSleepHabits().first()
                val todayProgress = DailySleepProgress(
                    date = LocalDate.now(),
                    habits = allHabits
                )
                sleepProgressRepository.saveDailyProgress(todayProgress)

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
