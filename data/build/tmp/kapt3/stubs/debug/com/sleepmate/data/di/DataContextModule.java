package com.sleepmate.data.di;

@dagger.Module()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u0007J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u0007J\u0012\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u0007J\u0012\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\rH\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/sleepmate/data/di/DataContextModule;", "", "<init>", "()V", "provideApplicationContext", "Landroid/content/Context;", "context", "provideAlarmDataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "provideHealthConnectClient", "Landroidx/health/connect/client/HealthConnectClient;", "provideDatabase", "Lcom/sleepmate/data/datasource/local/SleepMateDatabase;", "provideSleepHistoryDao", "Lcom/sleepmate/data/datasource/local/dao/SleepHistoryDao;", "database", "provideDailyHealthMetricsDao", "Lcom/sleepmate/data/datasource/local/dao/DailyHealthMetricsDao;", "data_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DataContextModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.sleepmate.data.di.DataContextModule INSTANCE = null;
    
    private DataContextModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context provideApplicationContext(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> provideAlarmDataStore(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final androidx.health.connect.client.HealthConnectClient provideHealthConnectClient(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.sleepmate.data.datasource.local.SleepMateDatabase provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.sleepmate.data.datasource.local.dao.SleepHistoryDao provideSleepHistoryDao(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.datasource.local.SleepMateDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.sleepmate.data.datasource.local.dao.DailyHealthMetricsDao provideDailyHealthMetricsDao(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.datasource.local.SleepMateDatabase database) {
        return null;
    }
}