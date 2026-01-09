package com.sleepmate.domain.usecase;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0018\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0086B\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/sleepmate/domain/usecase/SyncDailyHealthMetricsUseCase;", "", "healthDataRepository", "Lcom/sleepmate/domain/repository/HealthDataRepository;", "dailyHealthMetricsRepository", "Lcom/sleepmate/domain/repository/DailyHealthMetricsRepository;", "onboardingUseCase", "Lcom/sleepmate/domain/usecase/OnboardingUseCase;", "<init>", "(Lcom/sleepmate/domain/repository/HealthDataRepository;Lcom/sleepmate/domain/repository/DailyHealthMetricsRepository;Lcom/sleepmate/domain/usecase/OnboardingUseCase;)V", "invoke", "", "days", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "domain_debug"})
public final class SyncDailyHealthMetricsUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.domain.repository.HealthDataRepository healthDataRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.domain.repository.DailyHealthMetricsRepository dailyHealthMetricsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.domain.usecase.OnboardingUseCase onboardingUseCase = null;
    
    @javax.inject.Inject()
    public SyncDailyHealthMetricsUseCase(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.HealthDataRepository healthDataRepository, @org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.DailyHealthMetricsRepository dailyHealthMetricsRepository, @org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.usecase.OnboardingUseCase onboardingUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(long days, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}