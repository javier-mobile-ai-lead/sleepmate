package com.sleepmate.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u000eJ*\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00070\u00110\u00102\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/sleepmate/data/repository/SleepProgressRepositoryImpl;", "Lcom/sleepmate/domain/repository/SleepProgressRepository;", "sleepProgressDataStore", "Lcom/sleepmate/data/datasource/local/SleepProgressDataStore;", "<init>", "(Lcom/sleepmate/data/datasource/local/SleepProgressDataStore;)V", "getDailyProgress", "Lcom/sleepmate/domain/model/DailySleepProgress;", "date", "Ljava/time/LocalDate;", "(Ljava/time/LocalDate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDailyProgress", "", "progress", "(Lcom/sleepmate/domain/model/DailySleepProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProgressForDateRange", "Lkotlinx/coroutines/flow/Flow;", "", "startDate", "endDate", "data_debug"})
public final class SleepProgressRepositoryImpl implements com.sleepmate.domain.repository.SleepProgressRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.data.datasource.local.SleepProgressDataStore sleepProgressDataStore = null;
    
    @javax.inject.Inject()
    public SleepProgressRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.datasource.local.SleepProgressDataStore sleepProgressDataStore) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getDailyProgress(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.sleepmate.domain.model.DailySleepProgress> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveDailyProgress(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.model.DailySleepProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.Map<java.time.LocalDate, com.sleepmate.domain.model.DailySleepProgress>> getProgressForDateRange(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate startDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate endDate) {
        return null;
    }
}