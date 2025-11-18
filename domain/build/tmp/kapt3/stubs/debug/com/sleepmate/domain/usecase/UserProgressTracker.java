package com.sleepmate.domain.usecase;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/sleepmate/domain/usecase/UserProgressTracker;", "", "trackerDataSource", "Lcom/sleepmate/domain/datasource/TrackerDataSource;", "<init>", "(Lcom/sleepmate/domain/datasource/TrackerDataSource;)V", "trackAndResetIfNeeded", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isProgressCompleted", "", "progress", "Lcom/sleepmate/domain/model/DailySleepProgress;", "domain_debug"})
public final class UserProgressTracker {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.domain.datasource.TrackerDataSource trackerDataSource = null;
    
    @javax.inject.Inject()
    public UserProgressTracker(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.datasource.TrackerDataSource trackerDataSource) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object trackAndResetIfNeeded(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final boolean isProgressCompleted(com.sleepmate.domain.model.DailySleepProgress progress) {
        return false;
    }
}