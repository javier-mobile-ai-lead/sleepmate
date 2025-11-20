package com.sleepmate.domain.datasource;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00a6@\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u0010H\u00a6@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0010H\u00a6@\u00a2\u0006\u0002\u0010\u0013J\u000e\u0010\u0014\u001a\u00020\bH\u00a6@\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J*\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u00182\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u001b\u00a8\u0006\u001c\u00c0\u0006\u0003"}, d2 = {"Lcom/sleepmate/domain/datasource/TrackerDataSource;", "", "getDailyProgress", "Lcom/sleepmate/domain/model/DailySleepProgress;", "date", "Ljava/time/LocalDate;", "(Ljava/time/LocalDate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDailyProgress", "", "progress", "(Lcom/sleepmate/domain/model/DailySleepProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDefaultHabits", "", "Lcom/sleepmate/domain/model/SleepHabit;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStreakCount", "", "saveStreakCount", "count", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetStreak", "getLastResetDate", "setLastResetDate", "getProgressForDateRange", "", "startDate", "endDate", "(Ljava/time/LocalDate;Ljava/time/LocalDate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "domain_debug"})
public abstract interface TrackerDataSource {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDailyProgress(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.sleepmate.domain.model.DailySleepProgress> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveDailyProgress(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.model.DailySleepProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDefaultHabits(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.sleepmate.domain.model.SleepHabit>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStreakCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveStreakCount(int count, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object resetStreak(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLastResetDate(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.time.LocalDate> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setLastResetDate(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProgressForDateRange(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate startDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Map<java.time.LocalDate, com.sleepmate.domain.model.DailySleepProgress>> $completion);
}