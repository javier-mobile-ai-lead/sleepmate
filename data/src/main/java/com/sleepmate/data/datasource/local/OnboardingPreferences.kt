package com.sleepmate.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sleepmate.domain.model.UserProfile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("onboarding_preferences")

    companion object {
        private val ONBOARDING_COMPLETED_KEY = booleanPreferencesKey("onboarding_completed")
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val USER_AGE_RANGE_KEY = stringPreferencesKey("user_age_range")
        private val USER_STRESS_LEVEL_KEY = intPreferencesKey("user_stress_level")
        private val USER_STIMULANT_CONSUMPTION_KEY = stringPreferencesKey("user_stimulant_consumption")
        private val USER_PREFERENCES_KEY = stringSetPreferencesKey("user_preferences")
        private val USER_GOALS_KEY = stringSetPreferencesKey("user_goals")
    }

    val isOnboardingCompleted: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[ONBOARDING_COMPLETED_KEY] ?: false
        }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED_KEY] = completed
        }
    }

    val userProfile: Flow<UserProfile> = context.dataStore.data.map { preferences ->
        UserProfile(
            name = preferences[USER_NAME_KEY] ?: "",
            ageRange = preferences[USER_AGE_RANGE_KEY] ?: "",
            stressLevel = preferences[USER_STRESS_LEVEL_KEY] ?: 3,
            stimulantConsumption = preferences[USER_STIMULANT_CONSUMPTION_KEY] ?: "",
            preferences = preferences[USER_PREFERENCES_KEY]?.toList() ?: emptyList(),
            goals = preferences[USER_GOALS_KEY]?.toList() ?: emptyList()
        )
    }

    suspend fun saveUserProfile(userProfile: UserProfile) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = userProfile.name
            preferences[USER_AGE_RANGE_KEY] = userProfile.ageRange
            preferences[USER_STRESS_LEVEL_KEY] = userProfile.stressLevel
            preferences[USER_STIMULANT_CONSUMPTION_KEY] = userProfile.stimulantConsumption
            preferences[USER_PREFERENCES_KEY] = userProfile.preferences.toSet()
            preferences[USER_GOALS_KEY] = userProfile.goals.toSet()
        }
    }
}
