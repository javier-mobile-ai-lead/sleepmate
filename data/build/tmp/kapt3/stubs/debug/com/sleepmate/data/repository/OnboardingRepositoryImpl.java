package com.sleepmate.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\tR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\t\u00a8\u0006\u0013"}, d2 = {"Lcom/sleepmate/data/repository/OnboardingRepositoryImpl;", "Lcom/sleepmate/domain/repository/OnboardingRepository;", "onboardingPreferences", "Lcom/sleepmate/data/datasource/local/OnboardingPreferences;", "<init>", "(Lcom/sleepmate/data/datasource/local/OnboardingPreferences;)V", "isOnboardingCompleted", "Lkotlinx/coroutines/flow/Flow;", "", "()Lkotlinx/coroutines/flow/Flow;", "setOnboardingCompleted", "", "completed", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "userProfile", "Lcom/sleepmate/domain/model/UserProfile;", "getUserProfile", "saveUserProfile", "(Lcom/sleepmate/domain/model/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class OnboardingRepositoryImpl implements com.sleepmate.domain.repository.OnboardingRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.data.datasource.local.OnboardingPreferences onboardingPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isOnboardingCompleted = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.sleepmate.domain.model.UserProfile> userProfile = null;
    
    @javax.inject.Inject()
    public OnboardingRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.datasource.local.OnboardingPreferences onboardingPreferences) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> isOnboardingCompleted() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setOnboardingCompleted(boolean completed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.sleepmate.domain.model.UserProfile> getUserProfile() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveUserProfile(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.model.UserProfile userProfile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}