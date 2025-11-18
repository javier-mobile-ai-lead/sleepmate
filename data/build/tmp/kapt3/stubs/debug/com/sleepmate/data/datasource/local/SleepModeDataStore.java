package com.sleepmate.data.datasource.local;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\"\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/sleepmate/data/datasource/local/SleepModeDataStore;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getSleepModeState", "Lkotlinx/coroutines/flow/Flow;", "Lcom/sleepmate/domain/model/SleepModeState;", "setSleepModeActivated", "", "isActivated", "", "finishTimestamp", "", "(ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "data_debug"})
public final class SleepModeDataStore {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> MODO_SUENO_ACTIVADO = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> TIEMPO_FIN_TEMPORIZADOR = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.sleepmate.data.datasource.local.SleepModeDataStore.Companion Companion = null;
    
    @javax.inject.Inject()
    public SleepModeDataStore(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.sleepmate.domain.model.SleepModeState> getSleepModeState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setSleepModeActivated(boolean isActivated, @org.jetbrains.annotations.Nullable()
    java.lang.String finishTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/sleepmate/data/datasource/local/SleepModeDataStore$Companion;", "", "<init>", "()V", "MODO_SUENO_ACTIVADO", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "TIEMPO_FIN_TEMPORIZADOR", "", "data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}