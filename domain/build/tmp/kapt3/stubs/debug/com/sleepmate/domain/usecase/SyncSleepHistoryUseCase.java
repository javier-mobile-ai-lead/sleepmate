package com.sleepmate.domain.usecase;

/**
 * Use case to synchronize sleep history from Health Connect to local database.
 * Usually fetches the last 30 days of data.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086B\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/sleepmate/domain/usecase/SyncSleepHistoryUseCase;", "", "healthDataRepository", "Lcom/sleepmate/domain/repository/HealthDataRepository;", "sleepHistoryRepository", "Lcom/sleepmate/domain/repository/SleepHistoryRepository;", "<init>", "(Lcom/sleepmate/domain/repository/HealthDataRepository;Lcom/sleepmate/domain/repository/SleepHistoryRepository;)V", "invoke", "", "days", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "domain_debug"})
public final class SyncSleepHistoryUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.domain.repository.HealthDataRepository healthDataRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.domain.repository.SleepHistoryRepository sleepHistoryRepository = null;
    
    @javax.inject.Inject()
    public SyncSleepHistoryUseCase(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.HealthDataRepository healthDataRepository, @org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.SleepHistoryRepository sleepHistoryRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(long days, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}