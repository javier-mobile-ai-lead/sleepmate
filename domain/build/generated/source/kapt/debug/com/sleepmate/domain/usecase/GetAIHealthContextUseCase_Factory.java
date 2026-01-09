package com.sleepmate.domain.usecase;

import com.sleepmate.domain.repository.DailyHealthMetricsRepository;
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
public final class GetAIHealthContextUseCase_Factory implements Factory<GetAIHealthContextUseCase> {
  private final Provider<DailyHealthMetricsRepository> repositoryProvider;

  private GetAIHealthContextUseCase_Factory(
      Provider<DailyHealthMetricsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetAIHealthContextUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetAIHealthContextUseCase_Factory create(
      Provider<DailyHealthMetricsRepository> repositoryProvider) {
    return new GetAIHealthContextUseCase_Factory(repositoryProvider);
  }

  public static GetAIHealthContextUseCase newInstance(DailyHealthMetricsRepository repository) {
    return new GetAIHealthContextUseCase(repository);
  }
}
