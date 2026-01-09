package com.sleepmate.app.service

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sleepmate.domain.usecase.SyncDailyHealthMetricsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

@HiltWorker
class SyncHealthWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val syncDailyHealthMetricsUseCase: SyncDailyHealthMetricsUseCase
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            Timber.d("Iniciando sincronización automática de salud...")
            // Sincronizamos los últimos 3 días para asegurar que no falte nada
            syncDailyHealthMetricsUseCase(days = 3)
            Timber.d("Sincronización automática completada con éxito.")
            Result.success()
        } catch (e: Exception) {
            Timber.e(e, "Error en la sincronización automática de salud: ${e.message}")
            // Si falla, reintentamos según la política de retroceso configurada
            Result.retry()
        }
    }
}
