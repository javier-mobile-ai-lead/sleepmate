package com.sleepmate.data.repository;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.sleepmate.data.datasource.local.OnboardingPreferences;
import com.sleepmate.domain.repository.DailyHealthMetricsRepository;
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
public final class GPTRepositoryImpl_Factory implements Factory<GPTRepositoryImpl> {
  private final Provider<FirebaseRemoteConfig> remoteConfigProvider;

  private final Provider<OnboardingPreferences> onboardingPreferencesProvider;

  private final Provider<DailyHealthMetricsRepository> healthMetricsRepositoryProvider;

  private GPTRepositoryImpl_Factory(Provider<FirebaseRemoteConfig> remoteConfigProvider,
      Provider<OnboardingPreferences> onboardingPreferencesProvider,
      Provider<DailyHealthMetricsRepository> healthMetricsRepositoryProvider) {
    this.remoteConfigProvider = remoteConfigProvider;
    this.onboardingPreferencesProvider = onboardingPreferencesProvider;
    this.healthMetricsRepositoryProvider = healthMetricsRepositoryProvider;
  }

  @Override
  public GPTRepositoryImpl get() {
    return newInstance(remoteConfigProvider.get(), onboardingPreferencesProvider.get(), healthMetricsRepositoryProvider.get());
  }

  public static GPTRepositoryImpl_Factory create(
      Provider<FirebaseRemoteConfig> remoteConfigProvider,
      Provider<OnboardingPreferences> onboardingPreferencesProvider,
      Provider<DailyHealthMetricsRepository> healthMetricsRepositoryProvider) {
    return new GPTRepositoryImpl_Factory(remoteConfigProvider, onboardingPreferencesProvider, healthMetricsRepositoryProvider);
  }

  public static GPTRepositoryImpl newInstance(FirebaseRemoteConfig remoteConfig,
      OnboardingPreferences onboardingPreferences,
      DailyHealthMetricsRepository healthMetricsRepository) {
    return new GPTRepositoryImpl(remoteConfig, onboardingPreferences, healthMetricsRepository);
  }
}
