package com.sleepmate.domain.usecase;

import com.sleepmate.domain.datasource.TrackerDataSource;
import com.sleepmate.domain.repository.SleepHabitRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class UserProgressTracker_Factory implements Factory<UserProgressTracker> {
  private final Provider<TrackerDataSource> trackerDataSourceProvider;

  private final Provider<SleepHabitRepository> sleepHabitRepositoryProvider;

  private UserProgressTracker_Factory(Provider<TrackerDataSource> trackerDataSourceProvider,
      Provider<SleepHabitRepository> sleepHabitRepositoryProvider) {
    this.trackerDataSourceProvider = trackerDataSourceProvider;
    this.sleepHabitRepositoryProvider = sleepHabitRepositoryProvider;
  }

  @Override
  public UserProgressTracker get() {
    return newInstance(trackerDataSourceProvider.get(), sleepHabitRepositoryProvider.get());
  }

  public static UserProgressTracker_Factory create(
      Provider<TrackerDataSource> trackerDataSourceProvider,
      Provider<SleepHabitRepository> sleepHabitRepositoryProvider) {
    return new UserProgressTracker_Factory(trackerDataSourceProvider, sleepHabitRepositoryProvider);
  }

  public static UserProgressTracker newInstance(TrackerDataSource trackerDataSource,
      SleepHabitRepository sleepHabitRepository) {
    return new UserProgressTracker(trackerDataSource, sleepHabitRepository);
  }
}
