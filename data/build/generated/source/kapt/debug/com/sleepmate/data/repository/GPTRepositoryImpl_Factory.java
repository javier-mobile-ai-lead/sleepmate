package com.sleepmate.data.repository;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
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
public final class GPTRepositoryImpl_Factory implements Factory<GPTRepositoryImpl> {
  private final Provider<FirebaseRemoteConfig> remoteConfigProvider;

  private final Provider<OnboardingPreferences> onboardingPreferencesProvider;

  private GPTRepositoryImpl_Factory(Provider<FirebaseRemoteConfig> remoteConfigProvider,
      Provider<OnboardingPreferences> onboardingPreferencesProvider) {
    this.remoteConfigProvider = remoteConfigProvider;
    this.onboardingPreferencesProvider = onboardingPreferencesProvider;
  }

  @Override
  public GPTRepositoryImpl get() {
    return newInstance(remoteConfigProvider.get(), onboardingPreferencesProvider.get());
  }

  public static GPTRepositoryImpl_Factory create(
      Provider<FirebaseRemoteConfig> remoteConfigProvider,
      Provider<OnboardingPreferences> onboardingPreferencesProvider) {
    return new GPTRepositoryImpl_Factory(remoteConfigProvider, onboardingPreferencesProvider);
  }

  public static GPTRepositoryImpl newInstance(FirebaseRemoteConfig remoteConfig,
      OnboardingPreferences onboardingPreferences) {
    return new GPTRepositoryImpl(remoteConfig, onboardingPreferences);
  }
}
