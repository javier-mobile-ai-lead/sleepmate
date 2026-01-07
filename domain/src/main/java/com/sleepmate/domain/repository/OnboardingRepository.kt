package com.sleepmate.domain.repository

import com.sleepmate.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    val isOnboardingCompleted: Flow<Boolean>
    suspend fun setOnboardingCompleted(completed: Boolean)
    
    val userProfile: Flow<UserProfile>
    suspend fun saveUserProfile(userProfile: UserProfile)
}
