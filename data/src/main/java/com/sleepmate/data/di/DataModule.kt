package com.sleepmate.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.sleepmate.data.datasource.local.TrackerDataStoreImpl
import com.sleepmate.data.repository.AIUsageRepositoryImpl
import com.sleepmate.data.repository.AlarmRepositoryImpl
import com.sleepmate.data.repository.DarkModeRepositoryImpl
import com.sleepmate.data.repository.GPTRepositoryImpl
import com.sleepmate.data.repository.NotificationPushRepositoryImpl
import com.sleepmate.data.repository.SleepHabitRepositoryImpl
import com.sleepmate.data.repository.SleepProgressRepositoryImpl
import com.sleepmate.data.repository.SleepTimerRepositoryImpl
import com.sleepmate.data.repository.VideoRepositoryImpl
import com.sleepmate.domain.datasource.TrackerDataSource
import com.sleepmate.domain.repository.AIUsageRepository
import com.sleepmate.domain.repository.AlarmRepository
import com.sleepmate.domain.repository.DarkModeRepository
import com.sleepmate.domain.repository.GPTRepository
import com.sleepmate.domain.repository.NotificationPushRepository
import com.sleepmate.domain.repository.SleepHabitRepository
import com.sleepmate.domain.repository.SleepProgressRepository
import com.sleepmate.domain.repository.SleepTimerRepository
import com.sleepmate.domain.repository.VideoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    
    @Binds
    @Singleton
    abstract fun bindSleepHabitRepository(
        sleepHabitRepositoryImpl: SleepHabitRepositoryImpl
    ): SleepHabitRepository
    
    @Binds
    @Singleton
    abstract fun bindSleepTimerRepository(
        sleepTimerRepositoryImpl: SleepTimerRepositoryImpl
    ): SleepTimerRepository
    
    @Binds
    @Singleton
    abstract fun bindVideoRepository(
        videoRepositoryImpl: VideoRepositoryImpl
    ): VideoRepository
    
    @Binds
    @Singleton
    abstract fun bindAIUsageRepository(
        aiUsageRepositoryImpl: AIUsageRepositoryImpl
    ): AIUsageRepository
    
    @Binds
    @Singleton
    abstract fun bindGPTRepository(
        gptRepositoryImpl: GPTRepositoryImpl
    ): GPTRepository
    
    @Binds
    @Singleton
    abstract fun bindDarkModeRepository(
        darkModeRepositoryImpl: DarkModeRepositoryImpl
    ): DarkModeRepository
    
    @Binds
    @Singleton
    abstract fun bindNotificationPushRepository(
        notificationPushRepositoryImpl: NotificationPushRepositoryImpl
    ): NotificationPushRepository
    
    @Binds
    @Singleton
    abstract fun bindSleepProgressRepository(
        sleepProgressRepositoryImpl: SleepProgressRepositoryImpl
    ): SleepProgressRepository
    
    @Binds
    @Singleton
    abstract fun bindTrackerDataSource(
        trackerDataStoreImpl: TrackerDataStoreImpl
    ): TrackerDataSource

    @Binds
    @Singleton
    abstract fun bindAlarmRepository(
        alarmRepositoryImpl: AlarmRepositoryImpl
    ): AlarmRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DataContextModule {
    
    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideAlarmDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("alarm_settings") }
        )
    }
}
