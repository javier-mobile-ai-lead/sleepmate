package com.sleepmate.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/sleepmate/data/repository/NotificationPushRepositoryImpl;", "Lcom/sleepmate/domain/repository/NotificationPushRepository;", "notificationPushPreferences", "Lcom/sleepmate/data/datasource/local/NotificationPushPreferences;", "<init>", "(Lcom/sleepmate/data/datasource/local/NotificationPushPreferences;)V", "isNotificationPushEnabled", "Lkotlinx/coroutines/flow/Flow;", "", "setNotificationPushEnabled", "", "enabled", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class NotificationPushRepositoryImpl implements com.sleepmate.domain.repository.NotificationPushRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.data.datasource.local.NotificationPushPreferences notificationPushPreferences = null;
    
    @javax.inject.Inject()
    public NotificationPushRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.datasource.local.NotificationPushPreferences notificationPushPreferences) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> isNotificationPushEnabled() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setNotificationPushEnabled(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}