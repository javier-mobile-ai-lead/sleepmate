package com.sleepmate.domain.usecase

import com.sleepmate.domain.model.SleepHabit
import com.sleepmate.domain.repository.SleepHabitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSleepHabitsUseCase @Inject constructor(
    private val repository: SleepHabitRepository
) {
    operator fun invoke(): Flow<List<SleepHabit>> {
        return repository.getSleepHabits()
    }
}