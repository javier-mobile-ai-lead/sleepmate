package com.sleepmate.domain.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\nJ*\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010\u00c0\u0006\u0003"}, d2 = {"Lcom/sleepmate/domain/repository/SleepProgressRepository;", "", "getDailyProgress", "Lcom/sleepmate/domain/model/DailySleepProgress;", "date", "Ljava/time/LocalDate;", "(Ljava/time/LocalDate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDailyProgress", "", "progress", "(Lcom/sleepmate/domain/model/DailySleepProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProgressForDateRange", "", "startDate", "endDate", "(Ljava/time/LocalDate;Ljava/time/LocalDate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "domain_debug"})
public abstract interface SleepProgressRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDailyProgress(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.sleepmate.domain.model.DailySleepProgress> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveDailyProgress(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.model.DailySleepProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProgressForDateRange(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate startDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Map<java.time.LocalDate, com.sleepmate.domain.model.DailySleepProgress>> $completion);
}