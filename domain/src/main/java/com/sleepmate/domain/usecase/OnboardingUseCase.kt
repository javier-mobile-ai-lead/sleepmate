package com.sleepmate.domain.usecase

import com.sleepmate.domain.model.UserProfile
import com.sleepmate.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    fun isOnboardingCompleted(): Flow<Boolean> = onboardingRepository.isOnboardingCompleted

    suspend fun setOnboardingCompleted(completed: Boolean) {
        onboardingRepository.setOnboardingCompleted(completed)
    }

    val userProfile: Flow<UserProfile> = onboardingRepository.userProfile

    suspend fun saveUserProfile(userProfile: UserProfile) {
        onboardingRepository.saveUserProfile(userProfile)
    }
}
