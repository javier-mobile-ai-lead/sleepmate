package com.sleepmate.data.repository

import com.sleepmate.data.datasource.local.OnboardingPreferences
import com.sleepmate.domain.model.UserProfile
import com.sleepmate.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingPreferences: OnboardingPreferences
) : OnboardingRepository {

    override val isOnboardingCompleted: Flow<Boolean> = onboardingPreferences.isOnboardingCompleted

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        onboardingPreferences.setOnboardingCompleted(completed)
    }

    override val userProfile: Flow<UserProfile> = onboardingPreferences.userProfile

    override suspend fun saveUserProfile(userProfile: UserProfile) {
        onboardingPreferences.saveUserProfile(userProfile)
    }
}
