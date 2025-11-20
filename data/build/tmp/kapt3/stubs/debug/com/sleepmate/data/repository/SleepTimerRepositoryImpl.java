package com.sleepmate.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ \u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096@\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/sleepmate/data/repository/SleepTimerRepositoryImpl;", "Lcom/sleepmate/domain/repository/SleepTimerRepository;", "sleepModeDataStore", "Lcom/sleepmate/data/datasource/local/SleepModeDataStore;", "<init>", "(Lcom/sleepmate/data/datasource/local/SleepModeDataStore;)V", "_sleepTimer", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/sleepmate/domain/model/SleepTimer;", "getSleepTimer", "Lkotlinx/coroutines/flow/Flow;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setSleepTimerActive", "", "isActive", "", "durationMinutes", "", "(ZILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSleepModeState", "Lcom/sleepmate/domain/model/SleepModeState;", "setSleepModeActivated", "isActivated", "finishTimestamp", "", "(ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class SleepTimerRepositoryImpl implements com.sleepmate.domain.repository.SleepTimerRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.data.datasource.local.SleepModeDataStore sleepModeDataStore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.sleepmate.domain.model.SleepTimer> _sleepTimer = null;
    
    @javax.inject.Inject()
    public SleepTimerRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.datasource.local.SleepModeDataStore sleepModeDataStore) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getSleepTimer(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<com.sleepmate.domain.model.SleepTimer>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setSleepTimerActive(boolean isActive, int durationMinutes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getSleepModeState(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<com.sleepmate.domain.model.SleepModeState>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setSleepModeActivated(boolean isActivated, @org.jetbrains.annotations.Nullable()
    java.lang.String finishTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}