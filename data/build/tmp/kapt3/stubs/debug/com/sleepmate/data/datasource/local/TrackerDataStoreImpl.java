package com.sleepmate.data.datasource.local;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0005\b\u0007\u0018\u0000 \'2\u00020\u0001:\u0001\'B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0018\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0096@\u00a2\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u001bH\u0096@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001bH\u0096@\u00a2\u0006\u0002\u0010\u001eJ\u000e\u0010\u001f\u001a\u00020\u0013H\u0096@\u00a2\u0006\u0002\u0010\u0019J\u0010\u0010 \u001a\u0004\u0018\u00010\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010!\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0011J*\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000e0#2\u0006\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/sleepmate/data/datasource/local/TrackerDataStoreImpl;", "Lcom/sleepmate/domain/datasource/TrackerDataSource;", "context", "Landroid/content/Context;", "sleepProgressRepository", "Lcom/sleepmate/domain/repository/SleepProgressRepository;", "sleepHabitRepository", "Lcom/sleepmate/domain/repository/SleepHabitRepository;", "<init>", "(Landroid/content/Context;Lcom/sleepmate/domain/repository/SleepProgressRepository;Lcom/sleepmate/domain/repository/SleepHabitRepository;)V", "dateFormatter", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "getDailyProgress", "Lcom/sleepmate/domain/model/DailySleepProgress;", "date", "Ljava/time/LocalDate;", "(Ljava/time/LocalDate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDailyProgress", "", "progress", "(Lcom/sleepmate/domain/model/DailySleepProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDefaultHabits", "", "Lcom/sleepmate/domain/model/SleepHabit;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStreakCount", "", "saveStreakCount", "count", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetStreak", "getLastResetDate", "setLastResetDate", "getProgressForDateRange", "", "startDate", "endDate", "(Ljava/time/LocalDate;Ljava/time/LocalDate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "data_debug"})
public final class TrackerDataStoreImpl implements com.sleepmate.domain.datasource.TrackerDataSource {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.domain.repository.SleepProgressRepository sleepProgressRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.domain.repository.SleepHabitRepository sleepHabitRepository = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> STREAK_COUNT_KEY = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> LAST_RESET_DATE_KEY = null;
    private final java.time.format.DateTimeFormatter dateFormatter = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.sleepmate.data.datasource.local.TrackerDataStoreImpl.Companion Companion = null;
    
    @javax.inject.Inject()
    public TrackerDataStoreImpl(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.SleepProgressRepository sleepProgressRepository, @org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.SleepHabitRepository sleepHabitRepository) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getDailyProgress(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.sleepmate.domain.model.DailySleepProgress> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveDailyProgress(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.model.DailySleepProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getDefaultHabits(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.sleepmate.domain.model.SleepHabit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getStreakCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveStreakCount(int count, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object resetStreak(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getLastResetDate(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.time.LocalDate> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setLastResetDate(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getProgressForDateRange(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate startDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Map<java.time.LocalDate, com.sleepmate.domain.model.DailySleepProgress>> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/sleepmate/data/datasource/local/TrackerDataStoreImpl$Companion;", "", "<init>", "()V", "STREAK_COUNT_KEY", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "LAST_RESET_DATE_KEY", "", "data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}