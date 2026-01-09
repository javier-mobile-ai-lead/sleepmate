package com.sleepmate.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.health.connect.client.HealthConnectClient
import androidx.room.Room
import com.sleepmate.data.datasource.local.SleepMateDatabase
import com.sleepmate.data.datasource.local.TrackerDataStoreImpl
import com.sleepmate.data.datasource.local.dao.DailyHealthMetricsDao
import com.sleepmate.data.datasource.local.dao.SleepHistoryDao
import com.sleepmate.data.health.HealthDataRepositoryImpl
import com.sleepmate.data.repository.*
import com.sleepmate.domain.datasource.TrackerDataSource
import com.sleepmate.domain.repository.*
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

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(
        onboardingRepositoryImpl: OnboardingRepositoryImpl
    ): OnboardingRepository

    @Binds
    @Singleton
    abstract fun bindHealthDataRepository(
        healthDataRepositoryImpl: HealthDataRepositoryImpl
    ): HealthDataRepository

    @Binds
    @Singleton
    abstract fun bindSleepHistoryRepository(
        sleepHistoryRepositoryImpl: SleepHistoryRepositoryImpl
    ): SleepHistoryRepository

    @Binds
    @Singleton
    abstract fun bindDailyHealthMetricsRepository(
        dailyHealthMetricsRepositoryImpl: DailyHealthMetricsRepositoryImpl
    ): DailyHealthMetricsRepository
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

    @Provides
    @Singleton
    fun provideHealthConnectClient(@ApplicationContext context: Context): HealthConnectClient {
        return HealthConnectClient.getOrCreate(context)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SleepMateDatabase {
        return Room.databaseBuilder(
            context,
            SleepMateDatabase::class.java,
            "sleepmate_db"
        )
        .fallbackToDestructiveMigration() // Facilitates development since schema is changing
        .build()
    }

    @Provides
    @Singleton
    fun provideSleepHistoryDao(database: SleepMateDatabase): SleepHistoryDao {
        return database.sleepHistoryDao()
    }

    @Provides
    @Singleton
    fun provideDailyHealthMetricsDao(database: SleepMateDatabase): DailyHealthMetricsDao {
        return database.dailyHealthMetricsDao()
    }
}
