package com.sleepmate.data.repository;

import com.sleepmate.data.datasource.local.OnboardingPreferences;
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
public final class OnboardingRepositoryImpl_Factory implements Factory<OnboardingRepositoryImpl> {
  private final Provider<OnboardingPreferences> onboardingPreferencesProvider;

  private OnboardingRepositoryImpl_Factory(
      Provider<OnboardingPreferences> onboardingPreferencesProvider) {
    this.onboardingPreferencesProvider = onboardingPreferencesProvider;
  }

  @Override
  public OnboardingRepositoryImpl get() {
    return newInstance(onboardingPreferencesProvider.get());
  }

  public static OnboardingRepositoryImpl_Factory create(
      Provider<OnboardingPreferences> onboardingPreferencesProvider) {
    return new OnboardingRepositoryImpl_Factory(onboardingPreferencesProvider);
  }

  public static OnboardingRepositoryImpl newInstance(OnboardingPreferences onboardingPreferences) {
    return new OnboardingRepositoryImpl(onboardingPreferences);
  }
}
