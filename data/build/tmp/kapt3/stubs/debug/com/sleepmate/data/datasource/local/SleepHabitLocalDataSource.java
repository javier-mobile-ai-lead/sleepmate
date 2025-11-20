package com.sleepmate.data.datasource.local;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/sleepmate/data/datasource/local/SleepHabitLocalDataSource;", "", "sleepHabitsDataStore", "Lcom/sleepmate/data/datasource/local/SleepHabitsDataStore;", "<init>", "(Lcom/sleepmate/data/datasource/local/SleepHabitsDataStore;)V", "getSleepHabits", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/sleepmate/domain/model/SleepHabit;", "addSleepHabit", "", "habit", "(Lcom/sleepmate/domain/model/SleepHabit;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSleepHabit", "deleteSleepHabit", "habitId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSleepHabitById", "data_debug"})
public final class SleepHabitLocalDataSource {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.data.datasource.local.SleepHabitsDataStore sleepHabitsDataStore = null;
    
    @javax.inject.Inject()
    public SleepHabitLocalDataSource(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.datasource.local.SleepHabitsDataStore sleepHabitsDataStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.sleepmate.domain.model.SleepHabit>> getSleepHabits() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addSleepHabit(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.model.SleepHabit habit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateSleepHabit(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.model.SleepHabit habit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteSleepHabit(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSleepHabitById(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.sleepmate.domain.model.SleepHabit> $completion) {
        return null;
    }
}