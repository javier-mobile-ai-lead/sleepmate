package com.sleepmate.domain.usecase;

import com.sleepmate.domain.repository.OnboardingRepository;
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
public final class OnboardingUseCase_Factory implements Factory<OnboardingUseCase> {
  private final Provider<OnboardingRepository> onboardingRepositoryProvider;

  private OnboardingUseCase_Factory(Provider<OnboardingRepository> onboardingRepositoryProvider) {
    this.onboardingRepositoryProvider = onboardingRepositoryProvider;
  }

  @Override
  public OnboardingUseCase get() {
    return newInstance(onboardingRepositoryProvider.get());
  }

  public static OnboardingUseCase_Factory create(
      Provider<OnboardingRepository> onboardingRepositoryProvider) {
    return new OnboardingUseCase_Factory(onboardingRepositoryProvider);
  }

  public static OnboardingUseCase newInstance(OnboardingRepository onboardingRepository) {
    return new OnboardingUseCase(onboardingRepository);
  }
}
