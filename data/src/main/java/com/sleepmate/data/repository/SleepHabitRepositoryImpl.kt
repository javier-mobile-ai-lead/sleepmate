package com.sleepmate.data.repository

import com.sleepmate.domain.model.SleepHabit
import com.sleepmate.domain.repository.SleepHabitRepository
import com.sleepmate.data.datasource.local.SleepHabitLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

class SleepHabitRepositoryImpl @Inject constructor(
    private val localDataSource: SleepHabitLocalDataSource
) : SleepHabitRepository {


    override suspend fun uncheckAllHabits() {
        localDataSource.uncheckAllHabits()
    }

    override fun getLastResetDate(): Flow<String?> {
        return localDataSource.getLastResetDate()
    }

    override suspend fun saveLastResetDate(date: String) {
        localDataSource.saveLastResetDate(date)
    }


    // --- AQUÍ ESTÁ EL CAMBIO CLAVE ---
    override fun getSleepHabits(): Flow<List<SleepHabit>> {
        return flow {
            // 1. Antes de entregar datos, verificamos la fecha
            val today = LocalDate.now().toString()

            // Obtenemos la fecha guardada (usamos .first() para leer una vez sincrónicamente)
            val lastReset = localDataSource.getLastResetDate().first()

            if (lastReset != today) {
                // 2. Si es un día nuevo, limpiamos la base de datos AHORA MISMO
                localDataSource.uncheckAllHabits()
                localDataSource.saveLastResetDate(today)
            }

            // 3. Con la BD ya limpia (si era necesario), emitimos el flujo de datos real
            // Cualquier cambio futuro en la BD se seguirá emitiendo automáticamente
            emitAll(localDataSource.getSleepHabits())
        }
    }
    
    override suspend fun addSleepHabit(habit: SleepHabit) {
        localDataSource.addSleepHabit(habit)
    }
    
    override suspend fun updateSleepHabit(habit: SleepHabit) {
        localDataSource.updateSleepHabit(habit)
    }
    
    override suspend fun deleteSleepHabit(habitId: String) {
        localDataSource.deleteSleepHabit(habitId)
    }
    
    override suspend fun getSleepHabitById(habitId: String): SleepHabit? {
        return localDataSource.getSleepHabitById(habitId)
    }
}