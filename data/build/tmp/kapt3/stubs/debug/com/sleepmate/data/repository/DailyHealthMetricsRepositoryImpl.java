package com.sleepmate.data.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0013J$\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0017J\f\u0010\u0018\u001a\u00020\t*\u00020\u0019H\u0002J\f\u0010\u001a\u001a\u00020\u0019*\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/sleepmate/data/repository/DailyHealthMetricsRepositoryImpl;", "Lcom/sleepmate/domain/repository/DailyHealthMetricsRepository;", "dao", "Lcom/sleepmate/data/datasource/local/dao/DailyHealthMetricsDao;", "<init>", "(Lcom/sleepmate/data/datasource/local/dao/DailyHealthMetricsDao;)V", "getRecentMetrics", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/sleepmate/domain/repository/DailyHealthMetrics;", "limit", "", "saveMetrics", "", "metrics", "(Lcom/sleepmate/domain/repository/DailyHealthMetrics;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMetricsForDate", "date", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMetricsInRange", "startDate", "endDate", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomain", "Lcom/sleepmate/data/datasource/local/entity/DailyHealthMetricsEntity;", "toEntity", "data_debug"})
public final class DailyHealthMetricsRepositoryImpl implements com.sleepmate.domain.repository.DailyHealthMetricsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.data.datasource.local.dao.DailyHealthMetricsDao dao = null;
    
    @javax.inject.Inject()
    public DailyHealthMetricsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.datasource.local.dao.DailyHealthMetricsDao dao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.sleepmate.domain.repository.DailyHealthMetrics>> getRecentMetrics(int limit) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveMetrics(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.DailyHealthMetrics metrics, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getMetricsForDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.sleepmate.domain.repository.DailyHealthMetrics> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getMetricsInRange(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.sleepmate.domain.repository.DailyHealthMetrics>> $completion) {
        return null;
    }
    
    private final com.sleepmate.domain.repository.DailyHealthMetrics toDomain(com.sleepmate.data.datasource.local.entity.DailyHealthMetricsEntity $this$toDomain) {
        return null;
    }
    
    private final com.sleepmate.data.datasource.local.entity.DailyHealthMetricsEntity toEntity(com.sleepmate.domain.repository.DailyHealthMetrics $this$toEntity) {
        return null;
    }
}