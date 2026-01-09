package com.sleepmate.domain.usecase.health;

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
public final class CheckHealthConnectAvailabilityUseCase_Factory implements Factory<CheckHealthConnectAvailabilityUseCase> {
  private final Provider<HealthDataRepository> healthDataRepositoryProvider;

  private CheckHealthConnectAvailabilityUseCase_Factory(
      Provider<HealthDataRepository> healthDataRepositoryProvider) {
    this.healthDataRepositoryProvider = healthDataRepositoryProvider;
  }

  @Override
  public CheckHealthConnectAvailabilityUseCase get() {
    return newInstance(healthDataRepositoryProvider.get());
  }

  public static CheckHealthConnectAvailabilityUseCase_Factory create(
      Provider<HealthDataRepository> healthDataRepositoryProvider) {
    return new CheckHealthConnectAvailabilityUseCase_Factory(healthDataRepositoryProvider);
  }

  public static CheckHealthConnectAvailabilityUseCase newInstance(
      HealthDataRepository healthDataRepository) {
    return new CheckHealthConnectAvailabilityUseCase(healthDataRepository);
  }
}
