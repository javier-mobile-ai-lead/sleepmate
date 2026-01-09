package com.sleepmate.domain.usecase

import com.sleepmate.domain.repository.DailyHealthMetrics
import com.sleepmate.domain.repository.DailyHealthMetricsRepository
import com.sleepmate.domain.repository.HealthDataRepository
import kotlinx.coroutines.flow.first
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class SyncDailyHealthMetricsUseCase @Inject constructor(
    private val healthDataRepository: HealthDataRepository,
    private val dailyHealthMetricsRepository: DailyHealthMetricsRepository,
    private val onboardingUseCase: OnboardingUseCase
) {
    suspend operator fun invoke(days: Long = 30) {
        val today = LocalDate.now()
        val zoneId = ZoneId.systemDefault()

        // Obtenemos el último peso y altura registrados para tenerlos como base
        val latestWeight = healthDataRepository.fetchLatestWeight()
        val latestHeight = healthDataRepository.fetchLatestHeight()

        // 1. Sincronizar métricas diarias
        for (i in 0 until days) {
            val date = today.minusDays(i)
            val dateString = date.format(DateTimeFormatter.ISO_LOCAL_DATE)
            
            val startOfDay = date.atStartOfDay(zoneId).toInstant()
            val endOfDay = date.plusDays(1).atStartOfDay(zoneId).toInstant().minusMillis(1)

            // Fetch all metrics for this day
            val sleepSessions = healthDataRepository.fetchSleepSessions(startOfDay, endOfDay)
            val totalSleepMinutes = sleepSessions.sumOf { it.durationMinutes }
            val steps = healthDataRepository.fetchTotalSteps(startOfDay, endOfDay)
            val calories = healthDataRepository.fetchTotalCaloriesBurned(startOfDay, endOfDay)
            val avgHeartRate = healthDataRepository.fetchAverageHeartRate(startOfDay, endOfDay)
            val restingHeartRate = healthDataRepository.fetchRestingHeartRate(startOfDay, endOfDay)
            val oxygen = healthDataRepository.fetchAverageOxygenSaturation(startOfDay, endOfDay)
            val hrv = healthDataRepository.fetchHeartRateVariability(startOfDay, endOfDay)
            
            // Identificamos el origen de los datos
            val sourceApp = healthDataRepository.fetchPrimarySourceApp(startOfDay, endOfDay)

            // Guardamos peso y altura en la métrica diaria (Room)
            // Para hoy, usamos los datos más recientes encontrados
            val metrics = DailyHealthMetrics(
                date = dateString,
                sleepDurationMinutes = totalSleepMinutes,
                steps = steps,
                caloriesBurned = calories,
                avgHeartRate = avgHeartRate,
                restingHeartRate = restingHeartRate,
                avgOxygenSaturation = oxygen,
                hrvRmssd = hrv,
                weight = if (i == 0L) latestWeight else null,
                height = if (i == 0L) latestHeight else null,
                sourceApp = sourceApp,
                lastSyncTime = Instant.now()
            )

            dailyHealthMetricsRepository.saveMetrics(metrics)
        }

        // 2. También actualizamos el perfil de usuario (opcional, para UI rápida)
        try {
            if (latestWeight != null || latestHeight != null) {
                val currentProfile = onboardingUseCase.userProfile.first()
                val updatedProfile = currentProfile.copy(
                    weight = latestWeight ?: currentProfile.weight,
                    height = latestHeight ?: currentProfile.height
                )
                onboardingUseCase.saveUserProfile(updatedProfile)
            }
        } catch (e: Exception) {
            // Error silencioso
        }
    }
}
