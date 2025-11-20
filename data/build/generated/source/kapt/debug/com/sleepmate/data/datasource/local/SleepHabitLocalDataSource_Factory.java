package com.sleepmate.data.datasource.local;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class SleepHabitLocalDataSource_Factory implements Factory<SleepHabitLocalDataSource> {
  private final Provider<SleepHabitsDataStore> sleepHabitsDataStoreProvider;

  private SleepHabitLocalDataSource_Factory(
      Provider<SleepHabitsDataStore> sleepHabitsDataStoreProvider) {
    this.sleepHabitsDataStoreProvider = sleepHabitsDataStoreProvider;
  }

  @Override
  public SleepHabitLocalDataSource get() {
    return newInstance(sleepHabitsDataStoreProvider.get());
  }

  public static SleepHabitLocalDataSource_Factory create(
      Provider<SleepHabitsDataStore> sleepHabitsDataStoreProvider) {
    return new SleepHabitLocalDataSource_Factory(sleepHabitsDataStoreProvider);
  }

  public static SleepHabitLocalDataSource newInstance(SleepHabitsDataStore sleepHabitsDataStore) {
    return new SleepHabitLocalDataSource(sleepHabitsDataStore);
  }
}
