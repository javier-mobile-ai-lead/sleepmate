package com.sleepmate.app.ui.screen.onboarding

import android.content.Intent
import androidx.health.connect.client.HealthConnectClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.domain.model.UserProfile
import com.sleepmate.domain.usecase.OnboardingUseCase
import com.sleepmate.domain.usecase.SyncDailyHealthMetricsUseCase
import com.sleepmate.domain.usecase.SyncSleepHistoryUseCase
import com.sleepmate.domain.usecase.health.CheckHealthConnectAvailabilityUseCase
import com.sleepmate.domain.usecase.health.GetGrantedHealthPermissionsUseCase
import com.sleepmate.domain.usecase.health.GetHealthConnectPermissionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HealthPermissionsState {
    object NotRequested : HealthPermissionsState()
    object AllGranted : HealthPermissionsState()
    data class PartiallyGranted(val grantedCount: Int, val totalCount: Int) : HealthPermissionsState()
    object Denied : HealthPermissionsState()
}

@HiltViewModel
class SetupProfileViewModel @Inject constructor(
    private val onboardingUseCase: OnboardingUseCase,
    private val checkHealthConnectAvailabilityUseCase: CheckHealthConnectAvailabilityUseCase,
    private val getHealthConnectPermissionsUseCase: GetHealthConnectPermissionsUseCase,
    private val getGrantedHealthPermissionsUseCase: GetGrantedHealthPermissionsUseCase,
    private val syncSleepHistoryUseCase: SyncSleepHistoryUseCase,
    private val syncDailyHealthMetricsUseCase: SyncDailyHealthMetricsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserProfile())
    val uiState: StateFlow<UserProfile> = _uiState.asStateFlow()

    private val _currentStep = MutableStateFlow(0)
    val currentStep: StateFlow<Int> = _currentStep.asStateFlow()

    private val _healthConnectAvailable = MutableStateFlow(false)
    val healthConnectAvailable: StateFlow<Boolean> = _healthConnectAvailable.asStateFlow()

    private val _permissionsState = MutableStateFlow<HealthPermissionsState>(HealthPermissionsState.NotRequested)
    val permissionsState: StateFlow<HealthPermissionsState> = _permissionsState.asStateFlow()

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing.asStateFlow()

    init {
        viewModelScope.launch {
            _healthConnectAvailable.value = checkHealthConnectAvailabilityUseCase().first()
            if (_healthConnectAvailable.value) {
                refreshPermissions(isInitialCheck = true)
            }
        }
    }

    fun updateName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun updateAge(age: Int) {
        _uiState.update { it.copy(age = age) }
    }

    fun updateStressLevel(level: Int) {
        _uiState.update { it.copy(stressLevel = level) }
    }

    fun updateCaffeineConsumption(consumption: String) {
        _uiState.update { it.copy(caffeineConsumption = consumption) }
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
            _isSyncing.value = true

            // 1. Sincronización de datos de salud
            if (_permissionsState.value is HealthPermissionsState.AllGranted ||
                _permissionsState.value is HealthPermissionsState.PartiallyGranted) {
                try {
                    syncSleepHistoryUseCase()
                    syncDailyHealthMetricsUseCase()
                } catch (e: Exception) {
                    // Ignorar errores de sincronización para no bloquear el flujo
                }
            }

            // 2. Guardar el perfil y marcar onboarding como completado
            onboardingUseCase.saveUserProfile(_uiState.value)
            onboardingUseCase.setOnboardingCompleted(true)

            // 3. Navegar
            onComplete()
            
            // NOTA: No reseteamos _isSyncing a false aquí para evitar el parpadeo 
            // de la pantalla anterior antes de que el Navigator cambie de Screen.
        }
    }


    fun getHealthConnectPermissions(): Set<String> {
        return getHealthConnectPermissionsUseCase()
    }

    fun refreshPermissions(isInitialCheck: Boolean = false) {
        viewModelScope.launch {
            val granted = getGrantedHealthPermissionsUseCase()
            val required = getHealthConnectPermissions()
            
            _permissionsState.value = when {
                granted.containsAll(required) -> HealthPermissionsState.AllGranted
                granted.isEmpty() -> {
                    if (isInitialCheck) HealthPermissionsState.NotRequested 
                    else HealthPermissionsState.Denied
                }
                else -> HealthPermissionsState.PartiallyGranted(granted.size, required.size)
            }
        }
    }

    fun onPermissionsResult(grantedPermissions: Set<String>) {
        refreshPermissions(isInitialCheck = false)
    }

    fun getHealthConnectSettingsIntent(): Intent {
        return Intent(HealthConnectClient.ACTION_HEALTH_CONNECT_SETTINGS)
    }
}
