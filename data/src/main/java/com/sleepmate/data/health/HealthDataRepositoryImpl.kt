package com.sleepmate.data.health

import android.content.Context
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.ExerciseSessionRecord
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.HeartRateVariabilityRmssdRecord
import androidx.health.connect.client.records.HeightRecord
import androidx.health.connect.client.records.OxygenSaturationRecord
import androidx.health.connect.client.records.RestingHeartRateRecord
import androidx.health.connect.client.records.SleepSessionRecord
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord
import androidx.health.connect.client.records.WeightRecord
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import com.sleepmate.domain.repository.HealthDataRepository
import com.sleepmate.domain.repository.SleepData
import com.sleepmate.domain.repository.SleepStage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Duration
import java.time.Instant
import javax.inject.Inject

/**
 * Concrete implementation of [HealthDataRepository] that interacts with the Health Connect SDK.
 */
class HealthDataRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : HealthDataRepository {

    private val healthConnectClient: HealthConnectClient? 
        get() = if (HealthConnectClient.getSdkStatus(context) == HealthConnectClient.SDK_AVAILABLE) {
            HealthConnectClient.getOrCreate(context)
        } else {
            null
        }

    override fun isHealthConnectAvailable(): Flow<Boolean> = flow {
        val status = HealthConnectClient.getSdkStatus(context)
        emit(status == HealthConnectClient.SDK_AVAILABLE || 
             status == HealthConnectClient.SDK_UNAVAILABLE_PROVIDER_UPDATE_REQUIRED)
    }

    override fun getRequiredPermissions(): Set<String> {
        return setOf(
            // Sleep
            HealthPermission.getReadPermission(SleepSessionRecord::class),
            HealthPermission.getWritePermission(SleepSessionRecord::class),
            
            // Physical Activity
            HealthPermission.getReadPermission(StepsRecord::class),
            HealthPermission.getReadPermission(ExerciseSessionRecord::class),
            HealthPermission.getReadPermission(TotalCaloriesBurnedRecord::class),
            
            // Vitals
            HealthPermission.getReadPermission(HeartRateRecord::class),
            HealthPermission.getReadPermission(RestingHeartRateRecord::class),
            HealthPermission.getReadPermission(OxygenSaturationRecord::class),
            HealthPermission.getReadPermission(HeartRateVariabilityRmssdRecord::class),

            // Body
            HealthPermission.getReadPermission(WeightRecord::class),
            HealthPermission.getReadPermission(HeightRecord::class)
        )
    }

    override suspend fun getGrantedPermissions(): Set<String> {
        return healthConnectClient?.permissionController?.getGrantedPermissions() ?: emptySet()
    }

    override suspend fun fetchSleepSessions(startTime: Instant, endTime: Instant): List<SleepData> {
        val client = healthConnectClient ?: return emptyList()
        return try {
            val response = client.readRecords(
                ReadRecordsRequest(
                    recordType = SleepSessionRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )
            response.records.map { record ->
                SleepData(
                    id = record.metadata.id,
                    startTime = record.startTime,
                    endTime = record.endTime,
                    durationMinutes = Duration.between(record.startTime, record.endTime).toMinutes(),
                    source = record.metadata.dataOrigin.packageName,
                    stages = record.stages.map { stage ->
                        SleepStage(
                            startTime = stage.startTime,
                            endTime = stage.endTime,
                            stageType = stage.stage
                        )
                    }
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun fetchTotalSteps(startTime: Instant, endTime: Instant): Long {
        val client = healthConnectClient ?: return 0L
        return try {
            val response = client.readRecords(
                ReadRecordsRequest(
                    recordType = StepsRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )
            response.records.sumOf { it.count }
        } catch (e: Exception) {
            0L
        }
    }

    override suspend fun fetchAverageHeartRate(startTime: Instant, endTime: Instant): Long? {
        val client = healthConnectClient ?: return null
        return try {
            val response = client.readRecords(
                ReadRecordsRequest(
                    recordType = HeartRateRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )
            if (response.records.isEmpty()) return null
            val allSamples = response.records.flatMap { it.samples }
            if (allSamples.isEmpty()) return null
            allSamples.map { it.beatsPerMinute }.average().toLong()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun fetchRestingHeartRate(startTime: Instant, endTime: Instant): Long? {
        val client = healthConnectClient ?: return null
        return try {
            val response = client.readRecords(
                ReadRecordsRequest(
                    recordType = RestingHeartRateRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )
            if (response.records.isEmpty()) return null
            response.records.map { it.beatsPerMinute }.average().toLong()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun fetchAverageOxygenSaturation(startTime: Instant, endTime: Instant): Double? {
        val client = healthConnectClient ?: return null
        return try {
            val response = client.readRecords(
                ReadRecordsRequest(
                    recordType = OxygenSaturationRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )
            if (response.records.isEmpty()) return null
            response.records.map { it.percentage.value }.average()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun fetchHeartRateVariability(startTime: Instant, endTime: Instant): Double? {
        val client = healthConnectClient ?: return null
        return try {
            val response = client.readRecords(
                ReadRecordsRequest(
                    recordType = HeartRateVariabilityRmssdRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )
            if (response.records.isEmpty()) return null
            response.records.map { it.heartRateVariabilityMillis }.average()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun fetchTotalCaloriesBurned(startTime: Instant, endTime: Instant): Long? {
        val client = healthConnectClient ?: return null
        return try {
            val response = client.readRecords(
                ReadRecordsRequest(
                    recordType = TotalCaloriesBurnedRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )
            if (response.records.isEmpty()) return null
            response.records.sumOf { it.energy.inKilocalories }.toLong()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun fetchLatestWeight(): Double? {
        val client = healthConnectClient ?: return null
        return try {
            val response = client.readRecords(
                ReadRecordsRequest(
                    recordType = WeightRecord::class,
                    timeRangeFilter = TimeRangeFilter.after(Instant.now().minus(Duration.ofDays(365)))
                )
            )
            response.records.lastOrNull()?.weight?.inKilograms
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun fetchLatestHeight(): Double? {
        val client = healthConnectClient ?: return null
        return try {
            val response = client.readRecords(
                ReadRecordsRequest(
                    recordType = HeightRecord::class,
                    timeRangeFilter = TimeRangeFilter.after(Instant.now().minus(Duration.ofDays(3650)))
                )
            )
            response.records.lastOrNull()?.height?.inMeters
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun fetchPrimarySourceApp(startTime: Instant, endTime: Instant): String? {
        val client = healthConnectClient ?: return null
        return try {
            // We prioritize Sleep Session source, then Steps as secondary
            val sleepResponse = client.readRecords(
                ReadRecordsRequest(
                    recordType = SleepSessionRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )
            if (sleepResponse.records.isNotEmpty()) {
                val pkgName = sleepResponse.records.first().metadata.dataOrigin.packageName
                return getAppName(pkgName)
            }

            val stepsResponse = client.readRecords(
                ReadRecordsRequest(
                    recordType = StepsRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )
            if (stepsResponse.records.isNotEmpty()) {
                val pkgName = stepsResponse.records.first().metadata.dataOrigin.packageName
                return getAppName(pkgName)
            }
            null
        } catch (e: Exception) {
            null
        }
    }

    private fun getAppName(packageName: String): String {
        return try {
            val packageManager = context.packageManager
            val info = packageManager.getApplicationInfo(packageName, 0)
            packageManager.getApplicationLabel(info).toString()
        } catch (e: Exception) {
            // Fallback for common apps if name cannot be retrieved
            when (packageName) {
                "com.google.android.apps.fitness" -> "Google Fit"
                "com.sec.android.app.shealth" -> "Samsung Health"
                else -> packageName
            }
        }
    }
}
