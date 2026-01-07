package com.sleepmate.app.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.domain.model.UserProfile
import com.sleepmate.domain.usecase.OnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetupProfileViewModel @Inject constructor(
    private val onboardingUseCase: OnboardingUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserProfile())
    val uiState: StateFlow<UserProfile> = _uiState.asStateFlow()

    private val _currentStep = MutableStateFlow(0)
    val currentStep: StateFlow<Int> = _currentStep.asStateFlow()

    fun updateName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun updateAgeRange(range: String) {
        _uiState.update { it.copy(ageRange = range) }
    }

    fun updateStressLevel(level: Int) {
        _uiState.update { it.copy(stressLevel = level) }
    }

    fun updateStimulantConsumption(consumption: String) {
        _uiState.update { it.copy(stimulantConsumption = consumption) }
    }

    fun togglePreference(preference: String) {
        _uiState.update { state ->
            val currentPrefs = state.preferences.toMutableList()
            if (currentPrefs.contains(preference)) {
                currentPrefs.remove(preference)
            } else {
                currentPrefs.add(preference)
            }
            state.copy(preferences = currentPrefs)
        }
    }

    fun toggleGoal(goal: String) {
        _uiState.update { state ->
            val currentGoals = state.goals.toMutableList()
            if (currentGoals.contains(goal)) {
                currentGoals.remove(goal)
            } else {
                currentGoals.add(goal)
            }
            state.copy(goals = currentGoals)
        }
    }

    fun nextStep() {
        _currentStep.update { it + 1 }
    }

    fun previousStep() {
        if (_currentStep.value > 0) {
            _currentStep.update { it - 1 }
        }
    }

    fun saveProfile(onComplete: () -> Unit) {
        viewModelScope.launch {
            onboardingUseCase.saveUserProfile(_uiState.value)
            onboardingUseCase.setOnboardingCompleted(true)
            onComplete()
        }
    }
}
