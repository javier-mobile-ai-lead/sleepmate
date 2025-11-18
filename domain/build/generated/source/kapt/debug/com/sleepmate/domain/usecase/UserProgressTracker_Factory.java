package com.sleepmate.domain.usecase;

import com.sleepmate.domain.datasource.TrackerDataSource;
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

  private UserProgressTracker_Factory(Provider<TrackerDataSource> trackerDataSourceProvider) {
    this.trackerDataSourceProvider = trackerDataSourceProvider;
  }

  @Override
  public UserProgressTracker get() {
    return newInstance(trackerDataSourceProvider.get());
  }

  public static UserProgressTracker_Factory create(
      Provider<TrackerDataSource> trackerDataSourceProvider) {
    return new UserProgressTracker_Factory(trackerDataSourceProvider);
  }

  public static UserProgressTracker newInstance(TrackerDataSource trackerDataSource) {
    return new UserProgressTracker(trackerDataSource);
  }
}
