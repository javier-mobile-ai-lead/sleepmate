package com.sleepmate.domain.repository;

/**
 * Defines the contract for accessing health-related data, such as sleep patterns,
 * activity, and vitals, abstracting the data source from the rest of the application.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a6@\u00a2\u0006\u0002\u0010\tJ$\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u0010J\u001e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u0010J \u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u0010J \u0010\u0014\u001a\u0004\u0018\u00010\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u0010J \u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u0010J \u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u0010J \u0010\u0018\u001a\u0004\u0018\u00010\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0016H\u00a6@\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0016H\u00a6@\u00a2\u0006\u0002\u0010\tJ \u0010\u001b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u001c\u00c0\u0006\u0003"}, d2 = {"Lcom/sleepmate/domain/repository/HealthDataRepository;", "", "isHealthConnectAvailable", "Lkotlinx/coroutines/flow/Flow;", "", "getRequiredPermissions", "", "", "getGrantedPermissions", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchSleepSessions", "", "Lcom/sleepmate/domain/repository/SleepData;", "startTime", "Ljava/time/Instant;", "endTime", "(Ljava/time/Instant;Ljava/time/Instant;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchTotalSteps", "", "fetchAverageHeartRate", "fetchRestingHeartRate", "fetchAverageOxygenSaturation", "", "fetchHeartRateVariability", "fetchTotalCaloriesBurned", "fetchLatestWeight", "fetchLatestHeight", "fetchPrimarySourceApp", "domain_debug"})
public abstract interface HealthDataRepository {
    
    /**
     * Checks if the Health Connect service is available and installed on the device.
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> isHealthConnectAvailable();
    
    /**
     * Returns the set of required permissions for the app to function properly.
     */
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.Set<java.lang.String> getRequiredPermissions();
    
    /**
     * Returns the set of permissions currently granted by the user.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getGrantedPermissions(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Set<java.lang.String>> $completion);
    
    /**
     * Fetches sleep sessions between the specified time range.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchSleepSessions(@org.jetbrains.annotations.NotNull()
    java.time.Instant startTime, @org.jetbrains.annotations.NotNull()
    java.time.Instant endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.sleepmate.domain.repository.SleepData>> $completion);
    
    /**
     * Fetches total steps taken between the specified time range.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchTotalSteps(@org.jetbrains.annotations.NotNull()
    java.time.Instant startTime, @org.jetbrains.annotations.NotNull()
    java.time.Instant endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Fetches average heart rate between the specified time range.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchAverageHeartRate(@org.jetbrains.annotations.NotNull()
    java.time.Instant startTime, @org.jetbrains.annotations.NotNull()
    java.time.Instant endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Fetches resting heart rate between the specified time range.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchRestingHeartRate(@org.jetbrains.annotations.NotNull()
    java.time.Instant startTime, @org.jetbrains.annotations.NotNull()
    java.time.Instant endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Fetches average oxygen saturation (SpO2) between the specified time range.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchAverageOxygenSaturation(@org.jetbrains.annotations.NotNull()
    java.time.Instant startTime, @org.jetbrains.annotations.NotNull()
    java.time.Instant endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    /**
     * Fetches heart rate variability (HRV) RMSSD between the specified time range.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchHeartRateVariability(@org.jetbrains.annotations.NotNull()
    java.time.Instant startTime, @org.jetbrains.annotations.NotNull()
    java.time.Instant endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    /**
     * Fetches total calories burned between the specified time range.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchTotalCaloriesBurned(@org.jetbrains.annotations.NotNull()
    java.time.Instant startTime, @org.jetbrains.annotations.NotNull()
    java.time.Instant endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Fetches the latest weight record from Health Connect.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchLatestWeight(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    /**
     * Fetches the latest height record from Health Connect.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchLatestHeight(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    /**
     * Fetches the primary source application for health data in a given range.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchPrimarySourceApp(@org.jetbrains.annotations.NotNull()
    java.time.Instant startTime, @org.jetbrains.annotations.NotNull()
    java.time.Instant endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
}