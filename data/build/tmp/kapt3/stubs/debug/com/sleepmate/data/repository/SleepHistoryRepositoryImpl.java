package com.sleepmate.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007H\u0016J\u001c\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0096@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\u0013J\f\u0010\u0014\u001a\u00020\t*\u00020\u0015H\u0002J\f\u0010\u0016\u001a\u00020\u0015*\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/sleepmate/data/repository/SleepHistoryRepositoryImpl;", "Lcom/sleepmate/domain/repository/SleepHistoryRepository;", "sleepHistoryDao", "Lcom/sleepmate/data/datasource/local/dao/SleepHistoryDao;", "<init>", "(Lcom/sleepmate/data/datasource/local/dao/SleepHistoryDao;)V", "getAllSleepHistory", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/sleepmate/domain/model/SleepHistory;", "saveSleepHistory", "", "history", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentHistory", "since", "Ljava/time/Instant;", "(Ljava/time/Instant;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomain", "Lcom/sleepmate/data/datasource/local/entity/SleepHistoryEntity;", "toEntity", "data_debug"})
public final class SleepHistoryRepositoryImpl implements com.sleepmate.domain.repository.SleepHistoryRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.data.datasource.local.dao.SleepHistoryDao sleepHistoryDao = null;
    
    @javax.inject.Inject()
    public SleepHistoryRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.datasource.local.dao.SleepHistoryDao sleepHistoryDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.sleepmate.domain.model.SleepHistory>> getAllSleepHistory() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveSleepHistory(@org.jetbrains.annotations.NotNull()
    java.util.List<com.sleepmate.domain.model.SleepHistory> history, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getRecentHistory(@org.jetbrains.annotations.NotNull()
    java.time.Instant since, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.sleepmate.domain.model.SleepHistory>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final com.sleepmate.domain.model.SleepHistory toDomain(com.sleepmate.data.datasource.local.entity.SleepHistoryEntity $this$toDomain) {
        return null;
    }
    
    private final com.sleepmate.data.datasource.local.entity.SleepHistoryEntity toEntity(com.sleepmate.domain.model.SleepHistory $this$toEntity) {
        return null;
    }
}