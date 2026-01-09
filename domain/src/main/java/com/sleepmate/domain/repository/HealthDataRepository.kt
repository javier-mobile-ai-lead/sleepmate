package com.sleepmate.domain.repository

import kotlinx.coroutines.flow.Flow
import java.time.Instant

/**
 * Defines the contract for accessing health-related data, such as sleep patterns,
 * activity, and vitals, abstracting the data source from the rest of the application.
 */
interface HealthDataRepository {

    /**
     * Checks if the Health Connect service is available and installed on the device.
     */
    fun isHealthConnectAvailable(): Flow<Boolean>

    /**
     * Returns the set of required permissions for the app to function properly.
     */
    fun getRequiredPermissions(): Set<String>

    /**
     * Returns the set of permissions currently granted by the user.
     */
    suspend fun getGrantedPermissions(): Set<String>

    /**
     * Fetches sleep sessions between the specified time range.
     */
    suspend fun fetchSleepSessions(startTime: Instant, endTime: Instant): List<SleepData>

    /**
     * Fetches total steps taken between the specified time range.
     */
    suspend fun fetchTotalSteps(startTime: Instant, endTime: Instant): Long

    /**
     * Fetches average heart rate between the specified time range.
     */
    suspend fun fetchAverageHeartRate(startTime: Instant, endTime: Instant): Long?

    /**
     * Fetches resting heart rate between the specified time range.
     */
    suspend fun fetchRestingHeartRate(startTime: Instant, endTime: Instant): Long?

    /**
     * Fetches average oxygen saturation (SpO2) between the specified time range.
     */
    suspend fun fetchAverageOxygenSaturation(startTime: Instant, endTime: Instant): Double?

    /**
     * Fetches heart rate variability (HRV) RMSSD between the specified time range.
     */
    suspend fun fetchHeartRateVariability(startTime: Instant, endTime: Instant): Double?

    /**
     * Fetches total calories burned between the specified time range.
     */
    suspend fun fetchTotalCaloriesBurned(startTime: Instant, endTime: Instant): Long?

    /**
     * Fetches the latest weight record from Health Connect.
     */
    suspend fun fetchLatestWeight(): Double?

    /**
     * Fetches the latest height record from Health Connect.
     */
    suspend fun fetchLatestHeight(): Double?

    /**
     * Fetches the primary source application for health data in a given range.
     */
    suspend fun fetchPrimarySourceApp(startTime: Instant, endTime: Instant): String?
}

data class SleepData(
    val id: String,
    val startTime: Instant,
    val endTime: Instant,
    val durationMinutes: Long,
    val source: String? = null,
    val stages: List<SleepStage> = emptyList()
)

data class SleepStage(
    val startTime: Instant,
    val endTime: Instant,
    val stageType: Int // Mapping to Health Connect stage types
)

data class DailyHealthMetrics(
    val date: String, // Format "yyyy-MM-dd"
    val sleepDurationMinutes: Long,
    val steps: Long,
    val caloriesBurned: Long?,
    val avgHeartRate: Long?,
    val restingHeartRate: Long?,
    val avgOxygenSaturation: Double?,
    val hrvRmssd: Double?,
    val weight: Double? = null,
    val height: Double? = null,
    val sourceApp: String? = null, // Added to identify the origin of the data
    val lastSyncTime: Instant = Instant.now()
)
