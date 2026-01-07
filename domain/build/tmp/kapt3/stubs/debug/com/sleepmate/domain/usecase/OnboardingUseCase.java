package com.sleepmate.domain.usecase;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u0011\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0013"}, d2 = {"Lcom/sleepmate/domain/usecase/OnboardingUseCase;", "", "onboardingRepository", "Lcom/sleepmate/domain/repository/OnboardingRepository;", "<init>", "(Lcom/sleepmate/domain/repository/OnboardingRepository;)V", "isOnboardingCompleted", "Lkotlinx/coroutines/flow/Flow;", "", "setOnboardingCompleted", "", "completed", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "userProfile", "Lcom/sleepmate/domain/model/UserProfile;", "getUserProfile", "()Lkotlinx/coroutines/flow/Flow;", "saveUserProfile", "(Lcom/sleepmate/domain/model/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "domain_debug"})
public final class OnboardingUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.domain.repository.OnboardingRepository onboardingRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.sleepmate.domain.model.UserProfile> userProfile = null;
    
    @javax.inject.Inject()
    public OnboardingUseCase(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.OnboardingRepository onboardingRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isOnboardingCompleted() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setOnboardingCompleted(boolean completed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.sleepmate.domain.model.UserProfile> getUserProfile() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveUserProfile(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.model.UserProfile userProfile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}