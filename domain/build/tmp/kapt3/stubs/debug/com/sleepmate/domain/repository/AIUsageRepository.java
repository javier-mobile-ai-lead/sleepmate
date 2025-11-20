package com.sleepmate.domain.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\r\u00c0\u0006\u0003"}, d2 = {"Lcom/sleepmate/domain/repository/AIUsageRepository;", "", "getInstallationId", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getServerTimestamp", "", "hasUsedAIToday", "", "installationId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordAIUsage", "", "domain_debug"})
public abstract interface AIUsageRepository {
    
    /**
     * Obtiene el Firebase Installation ID único del dispositivo
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getInstallationId(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    /**
     * Obtiene la hora actual del servidor de Firebase en UTC (timestamp)
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getServerTimestamp(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Verifica si el usuario ya utilizó la IA hoy
     * @param installationId ID único del dispositivo
     * @return true si ya utilizó la IA hoy, false en caso contrario
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object hasUsedAIToday(@org.jetbrains.annotations.NotNull()
    java.lang.String installationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    /**
     * Registra el uso de la IA en Firestore
     * @param installationId ID único del dispositivo
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object recordAIUsage(@org.jetbrains.annotations.NotNull()
    java.lang.String installationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}