package com.sleepmate.domain.usecase

import com.sleepmate.domain.repository.DailyHealthMetrics
import com.sleepmate.domain.repository.DailyHealthMetricsRepository
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Use case to retrieve and format health metrics for AI context.
 * It fetches the last 7 days of data and creates a readable summary for the LLM.
 */
class GetAIHealthContextUseCase @Inject constructor(
    private val repository: DailyHealthMetricsRepository
) {
    suspend operator fun invoke(): String {
        val metrics = repository.getRecentMetrics(7).first()
        
        if (metrics.isEmpty()) {
            return "No hay datos de salud recientes disponibles de Health Connect."
        }

        val summary = StringBuilder()
        summary.append("--- DATOS DE SALUD REALES (Últimos 7 días) ---\n")
        
        metrics.forEach { metric ->
            summary.append("Fecha: ${metric.date}\n")
            summary.append("- Sueño: ${metric.sleepDurationMinutes / 60}h ${metric.sleepDurationMinutes % 60}min\n")
            summary.append("- Pasos: ${metric.steps}\n")
            metric.caloriesBurned?.let { summary.append("- Calorías: $it kcal\n") }
            metric.avgHeartRate?.let { summary.append("- Ritmo Cardíaco Promedio: $it bpm\n") }
            metric.restingHeartRate?.let { summary.append("- Ritmo Cardíaco Reposo: $it bpm\n") }
            metric.hrvRmssd?.let { summary.append("- Variabilidad Cardíaca (HRV): ${it.toInt()} ms\n") }
            metric.avgOxygenSaturation?.let { summary.append("- Oxígeno (SpO2): ${it.toInt()}%\n") }
            summary.append("\n")
        }

        // Add a small analysis of trends
        val avgSleep = metrics.map { it.sleepDurationMinutes }.average()
        val avgSteps = metrics.map { it.steps }.average()
        
        summary.append("--- RESUMEN DE TENDENCIAS ---\n")
        summary.append("Promedio de sueño: ${(avgSleep / 60).toInt()}h ${(avgSleep % 60).toInt()}min\n")
        summary.append("Promedio de pasos: ${avgSteps.toInt()}\n")
        
        return summary.toString()
    }
}
