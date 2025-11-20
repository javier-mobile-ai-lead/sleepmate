package com.sleepmate.domain.usecase;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/sleepmate/domain/usecase/DarkModeUseCase;", "", "darkModeRepository", "Lcom/sleepmate/domain/repository/DarkModeRepository;", "<init>", "(Lcom/sleepmate/domain/repository/DarkModeRepository;)V", "isDarkModeEnabled", "Lkotlinx/coroutines/flow/Flow;", "", "setDarkModeEnabled", "", "enabled", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "domain_debug"})
public final class DarkModeUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.domain.repository.DarkModeRepository darkModeRepository = null;
    
    @javax.inject.Inject()
    public DarkModeUseCase(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.DarkModeRepository darkModeRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isDarkModeEnabled() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setDarkModeEnabled(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}