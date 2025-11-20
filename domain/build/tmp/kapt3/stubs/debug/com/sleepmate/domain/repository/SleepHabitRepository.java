package com.sleepmate.domain.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H&J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u00a6@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\rH\u00a6@\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u0010\u00c0\u0006\u0003"}, d2 = {"Lcom/sleepmate/domain/repository/SleepHabitRepository;", "", "getSleepHabits", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/sleepmate/domain/model/SleepHabit;", "addSleepHabit", "", "habit", "(Lcom/sleepmate/domain/model/SleepHabit;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSleepHabit", "deleteSleepHabit", "habitId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSleepHabitById", "domain_debug"})
public abstract interface SleepHabitRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.sleepmate.domain.model.SleepHabit>> getSleepHabits();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addSleepHabit(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.model.SleepHabit habit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateSleepHabit(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.model.SleepHabit habit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteSleepHabit(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSleepHabitById(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.sleepmate.domain.model.SleepHabit> $completion);
}