package com.sleepmate.data.di;

@dagger.Module()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\'J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\'J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\'J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\'J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\'J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\'J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\'H\'\u00a8\u0006("}, d2 = {"Lcom/sleepmate/data/di/DataModule;", "", "<init>", "()V", "bindSleepHabitRepository", "Lcom/sleepmate/domain/repository/SleepHabitRepository;", "sleepHabitRepositoryImpl", "Lcom/sleepmate/data/repository/SleepHabitRepositoryImpl;", "bindSleepTimerRepository", "Lcom/sleepmate/domain/repository/SleepTimerRepository;", "sleepTimerRepositoryImpl", "Lcom/sleepmate/data/repository/SleepTimerRepositoryImpl;", "bindVideoRepository", "Lcom/sleepmate/domain/repository/VideoRepository;", "videoRepositoryImpl", "Lcom/sleepmate/data/repository/VideoRepositoryImpl;", "bindAIUsageRepository", "Lcom/sleepmate/domain/repository/AIUsageRepository;", "aiUsageRepositoryImpl", "Lcom/sleepmate/data/repository/AIUsageRepositoryImpl;", "bindGPTRepository", "Lcom/sleepmate/domain/repository/GPTRepository;", "gptRepositoryImpl", "Lcom/sleepmate/data/repository/GPTRepositoryImpl;", "bindDarkModeRepository", "Lcom/sleepmate/domain/repository/DarkModeRepository;", "darkModeRepositoryImpl", "Lcom/sleepmate/data/repository/DarkModeRepositoryImpl;", "bindNotificationPushRepository", "Lcom/sleepmate/domain/repository/NotificationPushRepository;", "notificationPushRepositoryImpl", "Lcom/sleepmate/data/repository/NotificationPushRepositoryImpl;", "bindSleepProgressRepository", "Lcom/sleepmate/domain/repository/SleepProgressRepository;", "sleepProgressRepositoryImpl", "Lcom/sleepmate/data/repository/SleepProgressRepositoryImpl;", "bindTrackerDataSource", "Lcom/sleepmate/domain/datasource/TrackerDataSource;", "trackerDataStoreImpl", "Lcom/sleepmate/data/datasource/local/TrackerDataStoreImpl;", "data_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class DataModule {
    
    public DataModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.sleepmate.domain.repository.SleepHabitRepository bindSleepHabitRepository(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.repository.SleepHabitRepositoryImpl sleepHabitRepositoryImpl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.sleepmate.domain.repository.SleepTimerRepository bindSleepTimerRepository(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.repository.SleepTimerRepositoryImpl sleepTimerRepositoryImpl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.sleepmate.domain.repository.VideoRepository bindVideoRepository(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.repository.VideoRepositoryImpl videoRepositoryImpl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.sleepmate.domain.repository.AIUsageRepository bindAIUsageRepository(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.repository.AIUsageRepositoryImpl aiUsageRepositoryImpl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.sleepmate.domain.repository.GPTRepository bindGPTRepository(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.repository.GPTRepositoryImpl gptRepositoryImpl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.sleepmate.domain.repository.DarkModeRepository bindDarkModeRepository(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.repository.DarkModeRepositoryImpl darkModeRepositoryImpl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.sleepmate.domain.repository.NotificationPushRepository bindNotificationPushRepository(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.repository.NotificationPushRepositoryImpl notificationPushRepositoryImpl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.sleepmate.domain.repository.SleepProgressRepository bindSleepProgressRepository(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.repository.SleepProgressRepositoryImpl sleepProgressRepositoryImpl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.sleepmate.domain.datasource.TrackerDataSource bindTrackerDataSource(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.datasource.local.TrackerDataStoreImpl trackerDataStoreImpl);
}