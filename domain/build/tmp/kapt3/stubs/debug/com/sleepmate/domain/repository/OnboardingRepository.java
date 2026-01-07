package com.sleepmate.domain.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\u000eR\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005R\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\u0005\u00a8\u0006\u000f\u00c0\u0006\u0003"}, d2 = {"Lcom/sleepmate/domain/repository/OnboardingRepository;", "", "isOnboardingCompleted", "Lkotlinx/coroutines/flow/Flow;", "", "()Lkotlinx/coroutines/flow/Flow;", "setOnboardingCompleted", "", "completed", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "userProfile", "Lcom/sleepmate/domain/model/UserProfile;", "getUserProfile", "saveUserProfile", "(Lcom/sleepmate/domain/model/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "domain_debug"})
public abstract interface OnboardingRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> isOnboardingCompleted();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setOnboardingCompleted(boolean completed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.sleepmate.domain.model.UserProfile> getUserProfile();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveUserProfile(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.model.UserProfile userProfile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}