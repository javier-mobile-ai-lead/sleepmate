package com.sleepmate.domain.di;

import com.sleepmate.domain.datasource.TrackerDataSource;
import com.sleepmate.domain.usecase.UserProgressTracker;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DomainModule_ProvideUserProgressTrackerFactory implements Factory<UserProgressTracker> {
  private final Provider<TrackerDataSource> trackerDataSourceProvider;

  private DomainModule_ProvideUserProgressTrackerFactory(
      Provider<TrackerDataSource> trackerDataSourceProvider) {
    this.trackerDataSourceProvider = trackerDataSourceProvider;
  }

  @Override
  public UserProgressTracker get() {
    return provideUserProgressTracker(trackerDataSourceProvider.get());
  }

  public static DomainModule_ProvideUserProgressTrackerFactory create(
      Provider<TrackerDataSource> trackerDataSourceProvider) {
    return new DomainModule_ProvideUserProgressTrackerFactory(trackerDataSourceProvider);
  }

  public static UserProgressTracker provideUserProgressTracker(
      TrackerDataSource trackerDataSource) {
    return Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideUserProgressTracker(trackerDataSource));
  }
}
