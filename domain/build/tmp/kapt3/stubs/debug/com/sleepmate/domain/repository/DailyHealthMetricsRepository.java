package com.sleepmate.domain.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u000fJ$\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014\u00c0\u0006\u0003"}, d2 = {"Lcom/sleepmate/domain/repository/DailyHealthMetricsRepository;", "", "getRecentMetrics", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/sleepmate/domain/repository/DailyHealthMetrics;", "limit", "", "saveMetrics", "", "metrics", "(Lcom/sleepmate/domain/repository/DailyHealthMetrics;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMetricsForDate", "date", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMetricsInRange", "startDate", "endDate", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "domain_debug"})
public abstract interface DailyHealthMetricsRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.sleepmate.domain.repository.DailyHealthMetrics>> getRecentMetrics(int limit);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveMetrics(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.DailyHealthMetrics metrics, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMetricsForDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.sleepmate.domain.repository.DailyHealthMetrics> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMetricsInRange(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.sleepmate.domain.repository.DailyHealthMetrics>> $completion);
}