package com.sleepmate.data.repository;

import com.sleepmate.data.datasource.local.SleepHabitLocalDataSource;
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
public final class SleepHabitRepositoryImpl_Factory implements Factory<SleepHabitRepositoryImpl> {
  private final Provider<SleepHabitLocalDataSource> localDataSourceProvider;

  private SleepHabitRepositoryImpl_Factory(
      Provider<SleepHabitLocalDataSource> localDataSourceProvider) {
    this.localDataSourceProvider = localDataSourceProvider;
  }

  @Override
  public SleepHabitRepositoryImpl get() {
    return newInstance(localDataSourceProvider.get());
  }

  public static SleepHabitRepositoryImpl_Factory create(
      Provider<SleepHabitLocalDataSource> localDataSourceProvider) {
    return new SleepHabitRepositoryImpl_Factory(localDataSourceProvider);
  }

  public static SleepHabitRepositoryImpl newInstance(SleepHabitLocalDataSource localDataSource) {
    return new SleepHabitRepositoryImpl(localDataSource);
  }
}
