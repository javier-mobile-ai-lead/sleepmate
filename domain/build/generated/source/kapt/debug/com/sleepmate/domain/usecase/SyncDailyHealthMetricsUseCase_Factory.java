package com.sleepmate.domain.usecase;

import com.sleepmate.domain.repository.DailyHealthMetricsRepository;
import com.sleepmate.domain.repository.HealthDataRepository;
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
public final class SyncDailyHealthMetricsUseCase_Factory implements Factory<SyncDailyHealthMetricsUseCase> {
  private final Provider<HealthDataRepository> healthDataRepositoryProvider;

  private final Provider<DailyHealthMetricsRepository> dailyHealthMetricsRepositoryProvider;

  private final Provider<OnboardingUseCase> onboardingUseCaseProvider;

  private SyncDailyHealthMetricsUseCase_Factory(
      Provider<HealthDataRepository> healthDataRepositoryProvider,
      Provider<DailyHealthMetricsRepository> dailyHealthMetricsRepositoryProvider,
      Provider<OnboardingUseCase> onboardingUseCaseProvider) {
    this.healthDataRepositoryProvider = healthDataRepositoryProvider;
    this.dailyHealthMetricsRepositoryProvider = dailyHealthMetricsRepositoryProvider;
    this.onboardingUseCaseProvider = onboardingUseCaseProvider;
  }

  @Override
  public SyncDailyHealthMetricsUseCase get() {
    return newInstance(healthDataRepositoryProvider.get(), dailyHealthMetricsRepositoryProvider.get(), onboardingUseCaseProvider.get());
  }

  public static SyncDailyHealthMetricsUseCase_Factory create(
      Provider<HealthDataRepository> healthDataRepositoryProvider,
      Provider<DailyHealthMetricsRepository> dailyHealthMetricsRepositoryProvider,
      Provider<OnboardingUseCase> onboardingUseCaseProvider) {
    return new SyncDailyHealthMetricsUseCase_Factory(healthDataRepositoryProvider, dailyHealthMetricsRepositoryProvider, onboardingUseCaseProvider);
  }

  public static SyncDailyHealthMetricsUseCase newInstance(HealthDataRepository healthDataRepository,
      DailyHealthMetricsRepository dailyHealthMetricsRepository,
      OnboardingUseCase onboardingUseCase) {
    return new SyncDailyHealthMetricsUseCase(healthDataRepository, dailyHealthMetricsRepository, onboardingUseCase);
  }
}
