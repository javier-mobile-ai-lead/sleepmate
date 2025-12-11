package com.sleepmate.domain.repository

import com.sleepmate.domain.model.Alarm
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {

    fun getAlarm(): Flow<Alarm>

    suspend fun saveAlarm(alarm: Alarm)
}
