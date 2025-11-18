package com.sleepmate.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.domain.usecase.DarkModeUseCase
import com.sleepmate.domain.usecase.UserProgressTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val darkModeUseCase: DarkModeUseCase,
    private val userProgressTracker: UserProgressTracker
): ViewModel() {
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    init {
        viewModelScope.launch {
           darkModeUseCase.isDarkModeEnabled().collect{
                _isDarkTheme.value = it
            }
        }
        
        viewModelScope.launch {
            userProgressTracker.trackAndResetIfNeeded()
        }
    }

    fun setDarkTheme(enabled: Boolean) {
        viewModelScope.launch {
            darkModeUseCase.setDarkModeEnabled(enabled)
        }
    }

    fun toggleTheme() {
        setDarkTheme(!_isDarkTheme.value)
    }
}