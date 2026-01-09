package com.sleepmate.domain.usecase;

import com.sleepmate.domain.repository.HealthDataRepository;
import com.sleepmate.domain.repository.SleepHistoryRepository;
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
public final class SyncSleepHistoryUseCase_Factory implements Factory<SyncSleepHistoryUseCase> {
  private final Provider<HealthDataRepository> healthDataRepositoryProvider;

  private final Provider<SleepHistoryRepository> sleepHistoryRepositoryProvider;

  private SyncSleepHistoryUseCase_Factory(
      Provider<HealthDataRepository> healthDataRepositoryProvider,
      Provider<SleepHistoryRepository> sleepHistoryRepositoryProvider) {
    this.healthDataRepositoryProvider = healthDataRepositoryProvider;
    this.sleepHistoryRepositoryProvider = sleepHistoryRepositoryProvider;
  }

  @Override
  public SyncSleepHistoryUseCase get() {
    return newInstance(healthDataRepositoryProvider.get(), sleepHistoryRepositoryProvider.get());
  }

  public static SyncSleepHistoryUseCase_Factory create(
      Provider<HealthDataRepository> healthDataRepositoryProvider,
      Provider<SleepHistoryRepository> sleepHistoryRepositoryProvider) {
    return new SyncSleepHistoryUseCase_Factory(healthDataRepositoryProvider, sleepHistoryRepositoryProvider);
  }

  public static SyncSleepHistoryUseCase newInstance(HealthDataRepository healthDataRepository,
      SleepHistoryRepository sleepHistoryRepository) {
    return new SyncSleepHistoryUseCase(healthDataRepository, sleepHistoryRepository);
  }
}
