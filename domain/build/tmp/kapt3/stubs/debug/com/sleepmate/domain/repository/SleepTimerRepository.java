package com.sleepmate.domain.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\"\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\t2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00a6@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014\u00c0\u0006\u0003"}, d2 = {"Lcom/sleepmate/domain/repository/SleepTimerRepository;", "", "getSleepTimer", "Lkotlinx/coroutines/flow/Flow;", "Lcom/sleepmate/domain/model/SleepTimer;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setSleepTimerActive", "", "isActive", "", "durationMinutes", "", "(ZILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSleepModeState", "Lcom/sleepmate/domain/model/SleepModeState;", "setSleepModeActivated", "isActivated", "finishTimestamp", "", "(ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "domain_debug"})
public abstract interface SleepTimerRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSleepTimer(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<com.sleepmate.domain.model.SleepTimer>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setSleepTimerActive(boolean isActive, int durationMinutes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSleepModeState(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<com.sleepmate.domain.model.SleepModeState>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setSleepModeActivated(boolean isActivated, @org.jetbrains.annotations.Nullable()
    java.lang.String finishTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}