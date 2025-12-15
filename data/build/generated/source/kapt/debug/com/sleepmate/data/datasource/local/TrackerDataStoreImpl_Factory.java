package com.sleepmate.data.datasource.local;

import android.content.Context;
import com.sleepmate.domain.repository.SleepHabitRepository;
import com.sleepmate.domain.repository.SleepProgressRepository;
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
public final class TrackerDataStoreImpl_Factory implements Factory<TrackerDataStoreImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<SleepProgressRepository> sleepProgressRepositoryProvider;

  private final Provider<SleepHabitRepository> sleepHabitRepositoryProvider;

  private TrackerDataStoreImpl_Factory(Provider<Context> contextProvider,
      Provider<SleepProgressRepository> sleepProgressRepositoryProvider,
      Provider<SleepHabitRepository> sleepHabitRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.sleepProgressRepositoryProvider = sleepProgressRepositoryProvider;
    this.sleepHabitRepositoryProvider = sleepHabitRepositoryProvider;
  }

  @Override
  public TrackerDataStoreImpl get() {
    return newInstance(contextProvider.get(), sleepProgressRepositoryProvider.get(), sleepHabitRepositoryProvider.get());
  }

  public static TrackerDataStoreImpl_Factory create(Provider<Context> contextProvider,
      Provider<SleepProgressRepository> sleepProgressRepositoryProvider,
      Provider<SleepHabitRepository> sleepHabitRepositoryProvider) {
    return new TrackerDataStoreImpl_Factory(contextProvider, sleepProgressRepositoryProvider, sleepHabitRepositoryProvider);
  }

  public static TrackerDataStoreImpl newInstance(Context context,
      SleepProgressRepository sleepProgressRepository, SleepHabitRepository sleepHabitRepository) {
    return new TrackerDataStoreImpl(context, sleepProgressRepository, sleepHabitRepository);
  }
}
